package main.com.weather.jg.keystores;

import main.com.weather.jg.util.AppPropertiesReader;

import java.util.Optional;
import java.util.logging.Logger;

import static main.com.weather.jg.util.AppPropertiesReader.appProperties;

public class ExternalApi {

    static Logger log = Logger.getLogger(ExternalApi.class.getName());

    private static final String API_KEY_VAULT_PATH_PROPERTY = "api-key-vault-path";
    private static final String API_KEY_VAULT_PROPERTY = "api-key-vault-property";

    private void callExternalApi() {

        VaultStaticSecretLoader vault = new VaultStaticSecretLoader();

        Optional<String> apiKey = vault.getStringKeyEntry(
                AppPropertiesReader.getAppProperties().getProperty(API_KEY_VAULT_PATH_PROPERTY),
                AppPropertiesReader.getAppProperties().getProperty(API_KEY_VAULT_PROPERTY));

        if (apiKey.isEmpty()) {
            throw new IllegalStateException("Couldn't retrieve api key from Vault");
        }

        log.info(String.format("[Mock Call] External api called with API_KEY: %s",
                apiKey.get()));
    }

    public static String getExternalApiKey(){
        VaultStaticSecretLoader vault = new VaultStaticSecretLoader();

        Optional<String> apiKey = vault.getStringKeyEntry(
                appProperties.getProperty(API_KEY_VAULT_PATH_PROPERTY),
                appProperties.getProperty(API_KEY_VAULT_PROPERTY));

        if (apiKey.isEmpty()) {
            throw new IllegalStateException("Couldn't retrieve api key from Vault");
        }

        log.info(apiKey.get());

        return apiKey.orElse(null);
    }

}
