//package com.accumulate.business.utils;
//
//import com.accumulate.business.config.KubernetesConfig;
//import com.accumulate.business.exception.SaasException;
//import com.google.gson.JsonSyntaxException;
//import io.kubernetes.client.ApiException;
//import io.kubernetes.client.Configuration;
//import io.kubernetes.client.apis.AppsV1Api;
//import io.kubernetes.client.apis.CoreV1Api;
//import io.kubernetes.client.apis.ExtensionsV1beta1Api;
//import io.kubernetes.client.models.*;
//import io.kubernetes.client.util.Config;
//import io.kubernetes.client.util.Yaml;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ResourceUtils;
//
//import javax.annotation.PostConstruct;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//public class KubernetesUtil {
//    @Autowired
//    private KubernetesConfig config;
//
//    private CoreV1Api coreV1Api;
//    private AppsV1Api appsV1Api;
//    private ExtensionsV1beta1Api extensionsV1beta1Api;
//    private static final String ENV_DB_NAME_KEY = "DRORE_POSTGRES_NAME";
//
//    private String appTemplate;
//    private String templateAppName;
//    private List<String> urlPrefixes;
//    private Map<String, String> presetEnvs;
//
//    @PostConstruct
//    public void init() {
//        try {
//            presetEnvs = config.getEnv();
//            appTemplate = config.getAppTemplate();
//            urlPrefixes = config.getUrlPrefixes();
//            templateAppName = config.getTemplateAppName();
//
//            Configuration.setDefaultApiClient(Config.defaultClient());
//
//            coreV1Api = new CoreV1Api();
//            appsV1Api = new AppsV1Api();
//            extensionsV1beta1Api = new ExtensionsV1beta1Api();
//        } catch (IOException e) {
//            log.error("Init kubernetes client failed: ", e.getMessage());
//            throw new SaasException("初始化k8s客户端失败！", e);
//        }
//    }
//
//    /**
//     * 查询命名空间ns下的活动Pod
//     *
//     * @param ns namespace
//     * @return name of all running pods
//     */
//    public List<String> listAllPods(String ns) {
//        try {
//            V1PodList list;
//            if (StringUtils.isEmpty(ns)) {
//                list = coreV1Api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
//            } else {
//                list = coreV1Api.listNamespacedPod(ns, null, null, null, null, null, null, null, null, null);
//            }
//            return list.getItems().stream().map(p -> p.getMetadata().getName()).collect(Collectors.toList());
//        } catch (ApiException e) {
//            log.error("Query apiServer for all pods in ns {} failed: ", ns, e.getMessage());
//            throw new SaasException("查询命名空间" + ns + "内Pod失败", e);
//        }
//    }
//
//    /**
//     * 部署tenant application ns
//     *
//     * @param ns application namespace
//     */
//    public String create(String ns) {
//        try {
//            List<Object> components = loadFromTemplate(ns);
//            createNS((V1Namespace) components.get(0), ns);
//            createSecret((V1Secret) components.get(1), ns);
//            createING((V1beta1Ingress) components.get(2), ns);
//            createSVC((V1Service) components.get(3), ns);
//            createDeploy((V1Deployment) components.get(4), ns);
//            return getAnyUrl(ns);
//        } catch (SaasException e) {
//            log.error(e.getMessage());
//            clear(ns);
//            throw e;
//        }
//    }
//
//    public void delete(String ns) {
//        deleteNS(ns);
//    }
//
//    public void deleteNS(String ns) {
//        try {
//            V1DeleteOptions options = new V1DeleteOptions();
//            coreV1Api.deleteNamespace(ns, options, null, null, null, false, null);
//        } catch (ApiException e) {
//            log.error("Delete ns {} failed: ", ns, e.getMessage());
//            throw new SaasException("删除命名空间" + ns + "失败！", e);
//        } catch (JsonSyntaxException e) {
//            //this is one known bug, delete these codes if they fixed it
//            //https://github.com/kubernetes-client/java/issues/86
//            if (!e.getMessage().startsWith("java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT")) {
//                throw e;
//            }
//        }
//    }
//
//    public V1beta1IngressList getING(String id) {
//        try {
//            return extensionsV1beta1Api.listNamespacedIngress(id, null, null, null, null, null, null, null, null, null);
//        } catch (ApiException e) {
//            log.error("Query ingress {} failed: ", id, e.getMessage());
//            throw new SaasException("查询Ingress " + id + "失败！", e);
//        }
//    }
//
//    public void start(String ns) {
//        List<Object> components = loadFromTemplate(ns);
//        createING((V1beta1Ingress) components.get(2), ns);
//    }
//
//    public void stop(String ns) {
//        deleteING(ns);
//    }
//
//    private void deleteING(String ns) {
//        try {
//            V1DeleteOptions options = new V1DeleteOptions();
//            extensionsV1beta1Api.deleteNamespacedIngress(ns, ns, options, null, null, null, false, null);
//        } catch (ApiException e) {
//            log.error("Delete ingress {} failed: ", ns, e.getMessage());
//            throw new SaasException("删除Ingress " + ns + "失败！", e);
//        }
//    }
//
//    private List<Object> loadFromTemplate(String appName) {
//        try {
//            StringBuffer sb = new StringBuffer();
//            FileReader reader = new FileReader(ResourceUtils.getFile(appTemplate));
//            BufferedReader bufReader = new BufferedReader(reader);
//            bufReader.lines().forEach(l -> sb.append(l.replace(templateAppName, appName)).append("\r\n"));
//            return Yaml.loadAll(sb.toString());
//        } catch (IOException e) {
//            log.error("Read app template file {} failed: ", appTemplate, e.getMessage());
//            throw new SaasException("读取应用模板文件" + appTemplate + "失败！", e);
//        }
//    }
//
//    private void createDeploy(V1Deployment deploy, String ns) {
//        try {
//            List<V1EnvVar> envs = new ArrayList<V1EnvVar>();
//            envs.add(new V1EnvVar().name(ENV_DB_NAME_KEY).value(ns));
//            presetEnvs.forEach((k, v) -> envs.add(new V1EnvVar().name(k).value(v)));
//
//            deploy.getSpec().getTemplate().getSpec().getContainers().forEach(c -> {
//                if (c.getEnv() == null) {
//                    c.setEnv(envs);
//                } else {
//                    c.getEnv().addAll(envs);
//                }
//            });
//
//            appsV1Api.createNamespacedDeployment(ns, deploy, null, null, null);
//        } catch (ApiException e) {
//            log.error("Create deployment {} failed: ", ns, e.getMessage());
//            throw new SaasException("创建Deployment " + ns + "失败", e);
//        }
//    }
//
//    private void createSVC(V1Service service, String ns) {
//        try {
//            coreV1Api.createNamespacedService(ns, service, null, null, null);
//        } catch (ApiException e) {
//            log.error("Create service {} failed: ", ns, e.getMessage());
//            throw new SaasException("创建服务" + ns + "失败", e);
//        }
//    }
//
//    private void createING(V1beta1Ingress ing, String ns) {
//        try {
//            extensionsV1beta1Api.createNamespacedIngress(ns, ing, null, null, null);
//        } catch (ApiException e) {
//            log.error("Create ingress {} failed: ", ns, e.getMessage());
//            throw new SaasException("创建Ingress " + ns + "失败", e);
//        }
//    }
//
//    private void createSecret(V1Secret secret, String ns) {
//        try {
//            coreV1Api.createNamespacedSecret(ns, secret, null, null, null);
//        } catch (ApiException e) {
//            log.error("Create secret {} failed: ", ns, e.getMessage());
//            throw new SaasException("创建命名空间" + ns + "失败", e);
//        }
//    }
//
//    private void createNS(V1Namespace ns, String ns_) {
//        try {
//            coreV1Api.createNamespace(ns, null, null, null);
//        } catch (ApiException e) {
//            log.error("Create ns {} failed: ", ns_, e.getMessage());
//            throw new SaasException("创建命名空间" + ns_ + "失败", e);
//        }
//    }
//
//    private void clear(String ns) {
//        try {
//            delete(ns);
//        } catch (SaasException e) {
//            log.error("Clear ns {} failed: ", ns, e.getMessage());
//        }
//    }
//
//    private String getAnyUrl(String ns) {
//        Random random = new Random();
//        int index = random.nextInt(urlPrefixes.size());
//        return urlPrefixes.get(index) + ns + "-ui/";
//    }
//}
