package org.pysz.agata.client;

import org.pysz.agata.client.GMarquee.Direction;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.css.RGBColor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 *  show how to use the Marquee plugin
 */
public class Main implements EntryPoint {

 
    public void onModuleLoad() {


	 //create label with long text
	Label text1 = new Label(
	        "firstText is very loooooooooooooooooooooooooooooooooooo0000000oooooo0000ooooo000000oooooooooooooooooooooooooong");
	text1.setWidth("600px");
	text1.getElement().getStyle().setBackgroundColor(RGBColor.rgb(230, 240, 235).getCssName());
	RootPanel.get("marqueePanel").add(text1);

	// create marquee effect for long text
	final GMarquee marquee = new GMarquee(text1);
	marquee.setDirection(Direction.LEFT);

	//create label with short text
	Label text2 = new Label("second text is rather short");
	text2.setWidth("500px");
	text2.getElement().getStyle().setBackgroundColor(RGBColor.rgb(230, 220, 235).getCssName()); 
	RootPanel.get("marqueePanel").add(text2);
 
	//create marquee effect for short text
	final GMarquee marquee2 = new GMarquee(text2);
	marquee.setDirection(Direction.LEFT);

	// add button which runs the marquee animations
	Button runAnimation = new Button("run marquee animation");
	runAnimation.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent arg0) {
		marquee.run();
		marquee2.run();
	    }
	});
	RootPanel.get("runAnimationButton").add(runAnimation);
    }

}
