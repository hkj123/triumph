package com.accumulate.core.web;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.util.Date;

/**
 * Created by ChenChang on 2017/4/11.
 */
public class BaseController {

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Integer.class,
//                new CustomNumberEditor(Integer.class, true));
//        binder.registerCustomEditor(Long.class,
//                new CustomNumberEditor(Long.class, true));
//        binder.registerCustomEditor(byte[].class,
//                new ByteArrayMultipartFileEditor());
//        binder.registerCustomEditor(Date.class,
//                new JodaTimeEditor(true));
//    }
}
