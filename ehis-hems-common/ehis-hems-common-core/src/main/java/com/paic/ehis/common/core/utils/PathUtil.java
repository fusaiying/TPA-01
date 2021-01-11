package com.paic.ehis.common.core.utils;

public abstract class PathUtil {
    public static final String classPath = PathUtil.class.getClassLoader().getResource("").toString();
    public static final String webPath = classPath.substring(0,classPath.substring(0,classPath.lastIndexOf('/')).lastIndexOf('/') + 1 );
    public static final String appPath = webPath.substring(0, webPath.substring(0, webPath.lastIndexOf('/')).lastIndexOf('/') + 1);
    public static final String modelPath = appPath.substring(0, appPath.substring(0, appPath.lastIndexOf('/')).lastIndexOf('/') + 1);
    public static final String projectPath = modelPath.substring(0, modelPath.substring(0, modelPath.lastIndexOf('/')).lastIndexOf('/') + 1);
}
