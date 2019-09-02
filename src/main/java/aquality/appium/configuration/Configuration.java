package aquality.appium.configuration;

import aquality.appium.utils.JsonFile;

import java.io.IOException;
import java.io.UncheckedIOException;

public class Configuration implements IConfiguration{

    private static ThreadLocal<Configuration> instance = ThreadLocal.withInitial(Configuration::new);
    private final ITimeoutConfiguration timeoutConfiguration;
    private final IRetryConfiguration retryConfiguration;
    private final IApplicationProfile applicationProfile;
    private final ILoggerConfiguration loggerConfiguration;

    private Configuration() {
        JsonFile settings = getSettings();
        timeoutConfiguration = new TimeoutConfiguration(settings);
        retryConfiguration = new RetryConfiguration(settings);
        applicationProfile = new ApplicationProfile(settings);
        loggerConfiguration = new LoggerConfiguration(settings);
    }

    public static Configuration getInstance() {
        return instance.get();
    }

    @Override
    public IApplicationProfile getApplicationProfile() {
        return applicationProfile;
    }

    @Override
    public ITimeoutConfiguration getTimeoutConfiguration() {
        return timeoutConfiguration;
    }

    @Override
    public IRetryConfiguration getRetryConfiguration() {
        return retryConfiguration;
    }

    @Override
    public ILoggerConfiguration getLoggerConfiguration() {
        return loggerConfiguration;
    }

    private JsonFile getSettings() {
        String settingsProfile = System.getProperty("profile") == null ? "settings.json" : "settings." + System.getProperty("profile") + ".json";
        try{
            return new JsonFile(settingsProfile);
        }catch (IOException e){
            throw new UncheckedIOException(String.format("Browser profile assigned in file %1$s was not found in the root of resources directory", settingsProfile), e);
        }
    }
}