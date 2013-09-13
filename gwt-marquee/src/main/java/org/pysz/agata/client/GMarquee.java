package org.pysz.agata.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.plugins.effects.PropertiesAnimation.Easing;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Class which allows to create marquee effect for a widget. The widget contents
 * will be wrapped in two additional widgets and a continuous animation will be
 * run in order to move the contents in a loop.
 * 
 * Marquee effect can be run in two directions: left and right. User can also
 * choose the speed of animation.
 * 
 * @author agata
 * 
 */
public class GMarquee {

    enum Direction {
	LEFT, RIGHT
    };

    static Direction DEFAULT_DIRECTION = Direction.LEFT;

    Logger logger = Logger.getLogger("GMarquee");

    private Direction direction = DEFAULT_DIRECTION;

    // speed of the marquee animation
    private int speed = 5;

    // query which encapsulates widget with marqueed content
    GQuery widgetQuery;


    // function which runs marquee animation
    MFunction marqueefunction = new MFunction();


    // function which encapsulates moving effect
    class MFunction extends Function {

	// duration of the marquee animation
	int duration;

	// css which will be applied before every animation loop
	String startingCSS;

	// description of how the div should be animated
	String animationCss;

	public void prepareAnimationData() {
	    // get width of the innerPanel
	    GQuery innerQuery = widgetQuery.find(".g-marquee-inner");
	    int innerPanelWidth = innerQuery.width();
	    logger.log(Level.INFO, "innerPanelWidth " + innerPanelWidth);

	    // get the actual width of the original panel
	    int viewPortWidth = widgetQuery.width();
	    logger.log(Level.INFO, "viewPortWidth " + viewPortWidth);

	    if (direction.equals(Direction.LEFT)) {

		// move the text and hide it behind the right margin
		startingCSS = "margin-left:" + viewPortWidth + "px";
		// animation will last till the text will disappear behind the
		// left border
		animationCss = "margin-left:'-" + innerPanelWidth;
	    } else if (direction.equals(Direction.RIGHT)) {

		// move the text to the left and hide it behind the left margin
		startingCSS = "margin-left: -" + innerPanelWidth + "px";
		// animation will last till the text disappear behind the right
		// border
		animationCss = "margin-left:'+" + viewPortWidth;

	    } else {
		logger.log(Level.SEVERE, "not supported direction");
	    }

	    // adjust the speed
	    // the factor is set in that way that user can type speeds which are
	    // integrals starting from 1 till 100
	    final int usabilityFactor = 100;
	    duration = ((innerPanelWidth + viewPortWidth) / speed) * usabilityFactor;
	}

	public void f() {

	    // get the reference to the outerPanel - which will be moved in the
	    // animation
	    GQuery outerQuery = widgetQuery.find(".g-marquee-outer");

	    outerQuery.css(Properties.create(startingCSS));

	    outerQuery.animate(animationCss, duration, Easing.LINEAR, this);

	}
    };



    /**
     * construct the dom structure which allows moving the content of the given
     * widget
     * 
     * @param label
     */
    public GMarquee(Widget label) {

	widgetQuery = GQuery.$(label);

	widgetQuery.css(CSS.OVERFLOW.with(Overflow.HIDDEN));

	// wrap the contents of toMove panel, with additional div
	FlowPanel innerPanel = new FlowPanel();
	innerPanel.addStyleName("g-marquee-inner");
	innerPanel.getElement().getStyle().setFloat(Float.LEFT);
	widgetQuery.wrapInner(innerPanel.getElement());

	// wrap created div with another div which allows very long texts to be
	// displayed in one line
	FlowPanel outerPanel = new FlowPanel();
	outerPanel.getElement().getStyle().setWidth(10000.0, Unit.PX);
	outerPanel.addStyleName("g-marquee-outer");
	widgetQuery.wrapInner(outerPanel.getElement());

    }

    /**
     * calculate and prepare animation properties and run the marquee animation
     */
    public void run() {
	// prepare data
	marqueefunction.prepareAnimationData();
	// run animation
	marqueefunction.f();
    }

    public Direction getDirection() {
	return direction;
    }

    public void setDirection(Direction direction) {
	this.direction = direction;
    }

    public int getSpeed() {
	return speed;
    }

    public void setSpeed(int speed) {
	this.speed = speed;
    }

}
