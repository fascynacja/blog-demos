package org.pysz.agata.client;

import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.plugins.Plugin;

public class MyPlugin extends GQuery {

  // Register the plugin in GQuery plugin system and
  // set a shortcut to the class 
  public static final Class<MyPlugin> MyPlugin = GQuery.registerPlugin(
    MyPlugin.class, new Plugin<MyPlugin>() {
      public MyPlugin init(GQuery gq) {
        return new MyPlugin(gq);
      }
    });

  // Initialization
  public MyPlugin(GQuery gq) {
    super(gq);
  }

  // Add a new method to GQuery objects
  public MyPlugin newMethod() {
    // Write your code here
    return this;
  }

  // Override the default behavior of GQuery existing methods
  public MyPlugin clear() {
     
    // Write your code here.
    return this;
  }
}