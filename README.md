# Guidebook

## Repositories

#### Maven

```gradle
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

```gradle
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

## API Test

* Download last API version jar file and put into `plguins` folder.

* Add this simple example to your main class to test the plugin's functionality.

```gradle
code here
```

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