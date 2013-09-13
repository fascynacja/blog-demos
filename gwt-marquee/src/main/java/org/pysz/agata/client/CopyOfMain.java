package org.pysz.agata.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.css.RGBColor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CopyOfMain implements EntryPoint {

    FlowPanel outer;
    FlowPanel inner;
    GQuery innerPanelQuery;

    final Function animateFunction = new Function() {
	public void f() {
	    innerPanelQuery.animate("left:'0'", 2000).animate("left:'-200'", 2000, animateFunction);
	}
    };

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

	outer = constructOuterPanel();

	inner = constructInnerPanel();

	outer.add(inner);
	RootPanel.get("marqueePanel").add(outer);

	innerPanelQuery = GQuery.$(inner);

	Button runAnimation = new Button("run marquee animation");
	runAnimation.addClickHandler(new ClickHandler() {

	    public void onClick(ClickEvent arg0) {
		animateFunction.f();
	    }
	});
	RootPanel.get("runAnimationButton").add(runAnimation);
    }

    private FlowPanel constructOuterPanel() {
	FlowPanel panel = new FlowPanel();

	GQuery.$(panel).css(CSS.BACKGROUND.getCssName(), RGBColor.BLACK.getCssName());
	GQuery.$(panel).css(CSS.OVERFLOW.getCssName(), Overflow.HIDDEN.getCssName());

	GQuery.$(panel).css(CSS.WIDTH.getCssName(), "500");

	return panel;
    }

    private FlowPanel constructInnerPanel() {
	FlowPanel panel = new FlowPanel();
	GQuery.$(panel).css(CSS.BACKGROUND.getCssName(), RGBColor.BLUE.getCssName());
	GQuery.$(panel).css(CSS.COLOR.getCssName(), RGBColor.WHITE.getCssName());
	GQuery.$(panel).css(CSS.LEFT.getCssName(), "-100");
	GQuery.$(panel).css(CSS.POSITION.getCssName(), Position.RELATIVE.getCssName());
	GQuery.$(panel).css(CSS.WIDTH.getCssName(), "700");

	Label text1 = new Label(
		"firstText is very loooooooooooooooooooooooooooooooooooo0000000oooooo0000ooooo000000oooooooooooooooooooooooooong");
	panel.add(text1);

	Label text2 = new Label("secondText");
	panel.add(text2);

	return panel;
    }

}
