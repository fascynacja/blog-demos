package org.pysz.agata.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.plugins.effects.PropertiesAnimation.Easing;

public class MarqueeFunction extends Function {
   
    Logger logger = Logger.getLogger("MarqueeFunction");
    int containerwidth;
    String animationCss;
    GQuery queryWrapperOuter;

    public void f()

    {
	logger.log(Level.SEVERE, "containerwidth " + containerwidth);
	queryWrapperOuter.css("margin-left", containerwidth + "");

	logger.log(Level.SEVERE, "animationCss " + animationCss);
	queryWrapperOuter.animate(animationCss, 8200, Easing.LINEAR, MarqueeFunction.this);

    }
}
