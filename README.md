# Guidebook

## Repositories

#### Maven

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

#### Gradle

```gradle
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
		maven { url 'https://jitpack.io' }
	}
}
```

## Dependencies

#### Maven

```xml
<dependency>
	<groupId>com.github.yarixer</groupId>
	<artifactId>yx_core</artifactId>
	<version>0.5</version>
</dependency>
```

#### Gradle

```gradle
dependencies {
	    implementation 'com.github.yarixer:yx_core:0.5'
}
```

> [!IMPORTANT]  
> You can check last API version on jitpack.io
>
> [![](https://jitpack.io/v/yarixer/yx_core.svg)](https://jitpack.io/#yarixer/yx_core)

## API Test

* Download last API version jar file and put into `plguins` folder.

* Add this simple example to your main class to test the plugin's functionality.

<details>
  <summary>CODE HERE</summary>

```js
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
    
        //connect
        Yx_core core = Yx_core.getInstance();
        if (core == null) {
            getLogger().severe("Core plugin is not loaded! Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        //read
        int example1 = core.getYamlFileHandler()
                .readSection("testplugin1/myfile.yml", "age", Integer.class)
                .orElse(0);

        //write
        example1++;
        core.getYamlFileHandler().writeSection("testplugin1/myfile.yml", "age", example1);
		
        getLogger().info("TestPlugin1 enabled!");
    }
}
```

</details>

## API: How to use

### Simple way
#### - Write

template: core.getYamlFileHandler().writeSection(fileName, section, value);

```gradle
core.getYamlFileHandler().writeSection("myfile.yml","age", "31");
```


#### - Read

template: core.getYamlFileHandler().readSection(fileName, section, T.class);

```gradle
int age = core.getYamlFileHandler().readSection("myfile.yml","age", Integer.class).orElse(0);
```

#### - Delete

template: core.getYamlFileHandler().deleteSection(fileName, section, keyToDelete);

```gradle
core.getYamlFileHandler().deleteSection("player1.yml", "playerlvl", "lvl");
```


### Complicated way

__Soon...__