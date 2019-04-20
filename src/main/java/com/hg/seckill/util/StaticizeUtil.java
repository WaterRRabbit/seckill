package com.hg.seckill.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by YE
 * 2019-04-20 14:44
 */
public class StaticizeUtil {

    /**
     *
     * @param root
     * @param templateName
     * @param model
     * @return
     */
    public static String staticize(File root, String templateName, Map<String, Object> model) {
        Configuration configuration = null;
        ByteArrayOutputStream outputStream = null;
        String res = null;
        try {
            configuration = new Configuration(Configuration.getVersion());
            configuration.setDirectoryForTemplateLoading(root);
            configuration.setDefaultEncoding("UTF-8");
            outputStream = new ByteArrayOutputStream();
            Template template = configuration.getTemplate(templateName);
            template.process(model, new OutputStreamWriter(outputStream));
            res = new String(outputStream.toByteArray());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
