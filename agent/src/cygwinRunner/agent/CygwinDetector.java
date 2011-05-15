package cygwinRunner.agent;

import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.util.FileUtil;

import java.io.File;

public class CygwinDetector {
    private static final Logger LOG = Logger.getInstance(CygwinDetector.class.getName());

    public CygwinDetector() {
    }

    public CygwinInfo findCygwin() {
        final String home = "c:\\cygwin"; //TODO: get from bean
        final File path = FileUtil.getCanonicalFile(new File(home));

        final CygwinInfo info = new CygwinInfo(path);
        LOG.info("Found: " + info);

        return info;
    }
}
