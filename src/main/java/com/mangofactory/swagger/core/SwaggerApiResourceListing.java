package com.mangofactory.swagger.core;

import com.mangofactory.swagger.scanners.ApiListingReferenceScanner;
import com.wordnik.swagger.core.SwaggerSpec;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.model.ApiListingReference;
import com.wordnik.swagger.model.AuthorizationType;
import com.wordnik.swagger.model.ResourceListing;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

import static com.mangofactory.swagger.ScalaUtils.toList;
import static com.mangofactory.swagger.ScalaUtils.toOption;

public class SwaggerApiResourceListing {
   @Getter
   private ResourceListing resourceListing;
   @Getter
   @Setter
   private ApiInfo apiInfo;
   @Getter
   @Setter
   private List<AuthorizationType> authorizationTypes;
   @Getter
   @Setter
   private String resourceListingPath = "/api-docs";

   @Getter
   @Setter
   private ApiListingReferenceScanner apiListingReferenceScanner;

   private ServletContext servletContext;

   public SwaggerApiResourceListing(ServletContext servletContext) {
      this.servletContext = servletContext;
   }

   public void createResourceListing() {
      List<ApiListingReference> apiListingReferences = new ArrayList<ApiListingReference>();
      if (null != apiListingReferenceScanner) {
         apiListingReferenceScanner.scan();
         apiListingReferences = apiListingReferenceScanner.getApiListingReferences();
      }
      this.resourceListing = new ResourceListing("1", SwaggerSpec.version(), toList(apiListingReferences),
                                                 toList(authorizationTypes), toOption(apiInfo));

   }


}