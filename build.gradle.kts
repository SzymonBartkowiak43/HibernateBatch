plugins {
  java
  kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  // Hibernate ORM
  implementation("org.hibernate.orm:hibernate-core:7.0.5.Final")
  annotationProcessor("org.hibernate.orm:hibernate-processor:7.0.5.Final")

  // Validator
  implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
  implementation("org.glassfish:jakarta.el:4.0.2")

  // Agroal
  runtimeOnly("org.hibernate.orm:hibernate-agroal:7.0.5.Final")
  runtimeOnly("io.agroal:agroal-pool:2.5")

  // Log4j2
  runtimeOnly("org.apache.logging.log4j:log4j-core:2.24.1")

//  // H2 database
//  runtimeOnly("com.h2database:h2:2.3.232")
  // Postrgres
  runtimeOnly("org.postgresql:postgresql:42.7.3")

  // JUnit (testy)
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}

tasks.register<JavaExec>("run") {
  group = "application"
  description = "Run the main class"
  classpath = sourceSets["main"].runtimeClasspath
  mainClass.set("org.example.Main")
}