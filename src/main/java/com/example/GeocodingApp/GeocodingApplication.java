package com.example.GeocodingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GeocodingApplication {

	public static void main(String[] args) {

		SpringApplication.run(GeocodingApplication.class, args);

	}

}



/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/address/searchTerm").allowedOrigins("http://localhost:3000");
			}
		};
	}*/
/*String packageName = "com.example.GeocodingApp.configuration";
		String basePath = "src/main/resources";
		File inputJson = new File(basePath + File.separator + "input.json");
		File outputPojoDirectory = new File(basePath + File.separator + "convertedPojo");
		outputPojoDirectory.mkdirs();
		try {
			new GeocodingApplication().convertJsonToJavaClass(inputJson.toURI().toURL(), outputPojoDirectory, packageName, inputJson.getName().replace(".json", ""));
		} catch (IOException e) {
			System.out.println("Encountered issue while converting to pojo: " + e.getMessage());
			e.printStackTrace();
		}*/



/*public void convertJsonToJavaClass(URL inputJsonUrl, File outputJavaClassDirectory, String packageName, String javaClassName) throws IOException {
			JCodeModel jcodeModel = new JCodeModel();

			GenerationConfig config = new DefaultGenerationConfig() {
				@Override
				public boolean isGenerateBuilders() {
					return true;
				}

				@Override
				public SourceType getSourceType() {
					return SourceType.JSON;
				}
			};

			SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
			mapper.generate(jcodeModel, javaClassName, packageName, inputJsonUrl);

			jcodeModel.build(outputJavaClassDirectory);
		}*/




