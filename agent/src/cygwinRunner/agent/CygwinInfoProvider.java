package cygwinRunner.agent;

import jetbrains.buildServer.agent.BuildAgent;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.agent.AgentLifeCycleListener;
import jetbrains.buildServer.agent.AgentLifeCycleAdapter;


public class CygwinInfoProvider {
    private final BuildAgentConfiguration myConfig;

    public CygwinInfoProvider(@NotNull final BuildAgentConfiguration config,
                              @NotNull final EventDispatcher<AgentLifeCycleListener> events,
                              @NotNull final CygwinDetector detector) {
        myConfig = config;
        events.addListener(new AgentLifeCycleAdapter() {
            @Override
            public void beforeAgentConfigurationLoaded(@NotNull final BuildAgent agent) {
                registerDetectedCygwin(detector);
                events.removeListener(this);
            }
        });
    }

    private void registerDetectedCygwin(@NotNull final CygwinDetector detector) {
        detector.findCygwin().saveInfo(myConfig);
    }

    @NotNull
    public CygwinInfo getCygwin() {
        return CygwinInfo.loadInfo(myConfig);
    }
}
