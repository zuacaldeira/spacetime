package org.spacetime.components;

import com.vaadin.server.Page;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * Created by zua on 19/10/16.
 */
public abstract class RelativeLayout extends AbsoluteLayout implements Page.BrowserWindowResizeListener {

    private float browserWidth;
    private float browserHeight;

    public RelativeLayout() {
        this(UI.getCurrent().getPage().getBrowserWindowWidth(), UI.getCurrent().getPage().getBrowserWindowHeight());
    }

    public RelativeLayout(float width, float height) {
        browserWidth = UI.getCurrent().getPage().getBrowserWindowWidth();
        browserHeight = UI.getCurrent().getPage().getBrowserWindowHeight();
        setWidth(width, Unit.PIXELS);
        setHeight(height, Unit.PIXELS);
        Page.getCurrent().addBrowserWindowResizeListener(this);
    }

    public void addComponentAt(Component c, float xPercentage, float yPercentage) {
        addComponent(c);
        ComponentPosition cPosition = getNormalizedPosition(c, xPercentage, yPercentage);
        setPosition(c, cPosition);
    }

    private ComponentPosition getNormalizedPosition(Component c, float xPercentage, float yPercentage) {
        ComponentPosition cPosition = new ComponentPosition();
        cPosition.setLeft(xPercentage, Unit.PERCENTAGE);
        cPosition.setBottom(yPercentage, Unit.PERCENTAGE);

        c.setSizeUndefined();
        float w2 = c.getWidth() / 2;
        float h2 = c.getHeight() / 2;
        return cPosition;

    }

    @Override
    public void browserWindowResized(Page.BrowserWindowResizeEvent browserWindowResizeEvent) {
        // New browser dimensions
        float newBrowserWidth = browserWindowResizeEvent.getWidth();
        float newBrowserHeight = browserWindowResizeEvent.getHeight();

        // Scale factor
        float scaleX = newBrowserWidth/browserWidth;
        float scaleY = newBrowserHeight/browserHeight;

        // Store new browser dimensions
        browserWidth = newBrowserWidth;
        browserHeight = newBrowserHeight;

        // Scaled component dimentions
        float newWidth = getWidth() * scaleX;
        float newHeight = getHeight() * scaleY;

        // Update component dimensions
        setWidth(newWidth, Unit.PIXELS);
        setHeight(newHeight, Unit.PIXELS);
    }

    private void updatePosition(Component component, float deltaWidth, float deltaHeight) {
        ComponentPosition position = getPosition(component);
        position.setLeft(position.getLeftValue()*deltaWidth, Unit.PIXELS);
        position.setBottom(position.getBottomValue()*deltaHeight, Unit.PIXELS);

        // Translate (-w/2, -h/2)
        //Float w2 = component.getWidth()/2;
        //Float h2 = component.getHeight()/2;
        //translate(position, w2, h2);
    }

    private void updateComponentSize(Component component, float deltaWidth, float deltaHeight) {
        component.setWidth(component.getWidth()*deltaWidth, Unit.PIXELS);
        component.setHeight(component.getHeight()*deltaHeight, Unit.PIXELS);
    }


    private void translate(ComponentPosition position, Float w2, Float h2) {
        position.setLeft(position.getLeftValue()-w2, Unit.PIXELS);
        position.setBottom(position.getBottomValue()-h2, Unit.PIXELS);
    }
}
