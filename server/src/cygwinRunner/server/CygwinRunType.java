package cygwinRunner.server;

import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.InvalidProperty;

import java.util.*;
import cygwinRunner.common.Util;

public class CygwinRunType extends RunType {
    private final PluginDescriptor myDescriptor;

    public CygwinRunType(@NotNull RunTypeRegistry reg,
                         @NotNull final PluginDescriptor descriptor) {
        myDescriptor = descriptor;
        reg.registerRunType(this);
    }

    @NotNull
    @Override
    public String getType() {
        return Util.RUN_TYPE;
    }

    @Override
    public String getDisplayName() {
        return "Cygwin";
    }

    @Override
    public String getDescription() {
        return "Cygwin runner";
    }

    @Override
    public PropertiesProcessor getRunnerPropertiesProcessor() {
        return new PropertiesProcessor() {
            public Collection<InvalidProperty> process(final Map<String, String> properties) {
                Collection<InvalidProperty> col = new ArrayList<InvalidProperty>();

                if (StringUtil.isEmptyOrSpaces(properties.get(Util.CYGWIN_LOCATION))) {
                    col.add(new InvalidProperty(Util.CYGWIN_LOCATION, "Location should not be empty"));
                }
                if (StringUtil.isEmptyOrSpaces(properties.get(Util.RUNNER_SCRIPT_CODE))) {
                    col.add(new InvalidProperty(Util.RUNNER_SCRIPT_CODE, "Code should not be empty"));
                }

                return col;
            }
        };
    }

    @Override
    public String getEditRunnerParamsJspFilePath() {
        return myDescriptor.getPluginResourcesPath("editCygwin.jsp");
    }

    @Override
    public String getViewRunnerParamsJspFilePath() {
        return myDescriptor.getPluginResourcesPath("viewCygwin.jsp");
    }

    @Override
    public Map<String, String> getDefaultRunnerProperties() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(Util.CYGWIN_LOCATION, "c:\\cygwin\\");
        return map;
    }

    @NotNull
    @Override
    public String describeParameters(@NotNull final Map<String, String> parameters) {
        StringBuilder sb = new StringBuilder("Cygwin ");

        sb.append("<script>"); // We are running only scripts, not files
        
        return sb.toString();
    }
  
    //@Override
    //public List<Requirement> getRunnerSpecificRequirements(@NotNull final Map<String, String> runParameters) {
    //    final PowerShellBitness bit = getBitness(runParameters);
    //    if (bit != null) {
    //        return Collections.singletonList(new Requirement(bit.getVersionKey(), null, RequirementType.EXISTS));
    //    }
    //    return Collections.emptyList();
    //}
}


