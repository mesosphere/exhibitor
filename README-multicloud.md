# Multicloud

This branch adds support for Azure Storage and Google Cloud Storage.

Only locking and shared configuration are implemented - backup support would need to be added separately.

## Pre-requisites

* ZooKeeper must be installed
* Exhibitor jar must be installed in your local maven repo
```bash
cd exhibitor/
./gradlew install
```

## Building

Copy the pom file into a new directory and build the fat jar with maven.

```bash
mkdir mydir
cp exhibitor/exhibitor-standalone/src/main/resources/buildscripts/standalone/maven/pom.xml mydir/
cd mydir
mvn clean package
```

## Running

#### Default ZK Configuration
Path should be passed to Exhibitor as `--defaultconfig`

Example file
```
log-index-directory=/opt/zookeeper/transactions
cleanup-period-ms=300000
check-ms=30000
backup-period-ms=600000
client-port=2181
cleanup-max-files=20
backup-max-store-ms=21600000
connect-port=2888
observer-threshold=0
election-port=3888
zoo-cfg-extra=tickTime\=2000&initLimit\=10&syncLimit\=5&quorumListenOnAllIPs\=true
auto-manage-instances-settling-period-ms=0
auto-manage-instances=1
```

### Google Cloud Storage

You'll need credentials associated with a [Service Account](https://cloud.google.com/storage/docs/authentication#service_accounts).

#### Options
```console
== Google Cloud Storage Options ==
    --gcscredentials <arg>   Optional credentials to use for gcsconfig.
                             Argument is the path to an Google Cloud
                             Storage credential properties file with three
                             properties:
                             com.netflix.exhibitor.gcs.account-email,
                             com.netflix.exhibitor.gcs.account-id, and
                             com.netflix.exhibitor.gcs.private-key-path

== Configuration Options for Type "gcs" ==
    --gcsconfig <arg>         The bucket name and key to store the config
                              (gcscredentials may be provided as well).
                              Argument is [bucket name]:[key].
    --gcsconfigprefix <arg>   When using Google Cloud Storage shared
                              config files, the prefix to use for values
                              such as locks. Default is exhibitor-
```

#### Credentials File
Specifies your Service Account credentials. Note the private key path should be the local path of the P12 (PKCS12)-formatted key.
```properties
com.netflix.exhibitor.gcs.account-email=<account-email>
com.netflix.exhibitor.gcs.account-id=<account-id>
com.netflix.exhibitor.gcs.private-key-path=<path-to-private-key>
```

#### Example
```bash
java -jar target/exhibitor-1.0.jar \
    --configtype=gcs \
    --gcsconfig=exhibitor-bucket:mycluster \
    --gcscredentials=credentials.properties \
    --defaultconfig=/path/to/defaults.conf
```

### Azure Storage

#### Options
```console
== Azure Options ==
    --azurecredentials <arg>   Optional credentials to use for
                               azureconfig. Argument is the path to an
                               Azure credential properties file with two
                               properties:
                               com.netflix.exhibitor.azure.account-name
                               and com.netflix.exhibitor.azure.account-key

== Configuration Options for Type "azure" ==
    --azureconfig <arg>   The container name and blob name to store the
                          config (azurecredentials may be provided as
                          well). Argument is [container name]:[blob name].
```

#### Credentials File
```properties
com.netflix.exhibitor.azure.account-name=<account-name>
com.netflix.exhibitor.azure.account-key=<account-key>
```

#### Example
```bash
java -jar target/exhibitor-1.0.jar \
    --configtype=azure \
    --azureconfig=mycontainer:mycluster \
    --azurecredentials=credentials.properties \
    --defaultconfig=/path/to/defaults.conf
```
