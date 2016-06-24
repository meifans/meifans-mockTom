package meifans.mocktom.comstants;

import java.io.File;

public class Constants {

    // System.getProperty("user.dir") return is the root path of project.
    public static final String SERVLET = System.getProperty("user.dir") + File.separator + "target" + File.separator

    + "classes";

    public static final String RESOURCE = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator;
}
