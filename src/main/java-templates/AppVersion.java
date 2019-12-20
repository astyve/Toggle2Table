/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnemployedVoodooFamily;

/**
 * Check https://blog.soebes.de/blog/2014/01/02/version-information-into-your-appas-with-maven/
 * @author asty
 */
public final class AppVersion {
    private static final String VERSION = "${project.version}";
    private static final String GROUPID = "${project.groupId}";
    private static final String ARTIFACTID = "${project.artifactId}";
    private static final String REVISION = "${buildNumber}";

    public static String getVersion() {
        return VERSION;
    }    
}
