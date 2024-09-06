package main.com.weather.jg.keystores;

import com.bettercloud.vault.SslConfig;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;
import main.com.weather.jg.util.AppPropertiesReader;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static main.com.weather.jg.util.AppPropertiesReader.appProperties;

public class VaultStaticSecretLoader {

    Logger log = Logger.getLogger(VaultStaticSecretLoader.class.getName());

    private static  final String VAULT_URL_PROPERTY =
            "vault.url";
    private static final String VAULT_TOKEN_PROPERTY = "%VAULT_TOKEN%";


    private final Vault vault;

    VaultStaticSecretLoader() {

        try {
            // Vault auth token, will be fetched from
            // environment variable VAULT_TOKEN
            // make sure you initialize it before starting the application

//            String token = System.getenv(VAULT_TOKEN_PROPERTY);
            String token = "hvs.veNhk3TnvzNqabCDTgGeKxBy";
            if (token == null) {
                throw new IllegalStateException("VAULT_TOKEN is not set");
            }

            final VaultConfig config = new VaultConfig().address(
                            AppPropertiesReader.getAppProperties().getProperty(VAULT_URL_PROPERTY))
                    .token(token)
                    // Disabling ssl for local usage
                    .sslConfig(new SslConfig().verify(false).build());
            // initializing vault with version 1
            // (for K/V secret engine compatibility)
            // check: https://github.com/BetterCloud/vault-java-driver
            vault = new Vault(config, 1);
            log.info("Vault initialized");

        } catch (VaultException e) {
            throw new IllegalStateException("Unable to initialize Vault", e);
        }
    }

    public Optional<String> getStringKeyEntry(String path, String key) {
        Optional<String> secret = Optional.empty();
        try {
            LogicalResponse response = vault.logical().read(path);
            secret = Optional.ofNullable(response.getData().get(key));
        } catch (VaultException e) {
            log.log(Level.SEVERE, "Unable to read secret from Vault", e);
        }
        return secret;
    }

}
