package org.spacetime;

import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 21/10/16.
 */
public abstract class SpacetimeView extends VerticalLayout implements Page.BrowserWindowResizeListener, Button.ClickListener{
    private SpacetimeMenu menu;
    private SpaceTimeBody body;
    private HorizontalLayout footer;

    public SpacetimeView() {
        setStyleName("spacetime");
        setSizeFull();
        setHeightUndefined();

        initMenu();
        initMain();
        addComponents(menu, body);

        setExpandRatio(menu, .1f);
        setExpandRatio(body, .9f);

        setComponentAlignment(menu, Alignment.MIDDLE_CENTER);
        setComponentAlignment(body, Alignment.MIDDLE_CENTER);
    }

    public SpacetimeMenu getMenu() {
        return menu;
    }

    public void setMenu(SpacetimeMenu menu) {
        this.menu = menu;
    }

    public SpaceTimeBody getBody() {
        return body;
    }

    public void setBody(SpaceTimeBody body) {
        this.body = body;
    }

    public HorizontalLayout getFooter() {
        return footer;
    }

    public void setFooter(HorizontalLayout footer) {
        this.footer = footer;
    }

    @Override
    public void browserWindowResized(Page.BrowserWindowResizeEvent browserWindowResizeEvent) {
        setWidth(browserWindowResizeEvent.getWidth(), Unit.PIXELS);
        setHeight(browserWindowResizeEvent.getHeight(), Unit.PIXELS);
    }

    protected String getDummyDescription1() {
        return "Lorem ipsum dolor sit amet, nunc pellentesque in aliquet " +
                "parturient nonummy quos, blandit nascetur, amet ante mauris " +
                "lacus ut viverra, ut quis dictumst aliquam arcu mauris egestas. " +
                "Libero nunc, pellentesque tincidunt amet convallis tristique aliquam, " +
                "turpis molestie in. Augue at. Est augue massa arcu phasellus risus, " +
                "lobortis a id vel convallis sed amet. Scelerisque molestie ante curabitur " +
                "morbi tincidunt. Proin sem libero enim leo vivamus faucibus, scelerisque adipisci, " +
                "at pellentesque massa vel ullamcorper imperdiet magna.";
    }

    protected  String getDummyDescription2() {
        return "Nulla pellentesque sed diam, diam a vel amet justo lacus at, erat sagittis " +
                "gravida vestibulum sagittis gravida, et interdum interdum semper, rutrum eu non sed. " +
                "Et suspendisse lectus dui pretium, turpis nibh ut vel massa dapibus, turpis sed " +
                "gravida pede vehicula id. Pellentesque a donec tortor, convallis viverra eros suscipit " +
                "dictumst eu, diam natoque metus at aliquam, sed sociis fusce adipiscing non eu lorem, " +
                "nullam tristique quis vestibulum integer. Maecenas faucibus ac dictumst. At bibendum mi quam " +
                "vehicula quam, euismod morbi morbi, pellentesque adipiscing sed ultricies dolor sagittis, " +
                "justo aenean, sint commodo. Nonummy commodo. Faucibus aliquam pellentesque eu et, dolor viverra " +
                "id tristique vitae dignissim. Nullam pede vestibulum justo, volutpat officiis cras, quis " +
                "dolor nulla arcu adipiscing sollicitudin a, donec dui libero magna non nec. Sed nunc nunc, " +
                "sed nullam dignissim ac ultricies, in non consequat. Nec pede consectetuer. Sed sagittis ut " +
                "duis elementum eros in, volutpat convallis rutrum aliquam ipsum rutrum nunc, viverra et magna " +
                "eros vestibulum. Amet a in, rutrum scelerisque potenti sapien, " +
                "ullamcorper at vestibulum eget, sollicitudin lacus duis dictum vel commodo eleifend.";
    }



    protected final void initMenu() {
        setMenu(createMenu());
    }
    protected final void initMain() {
        setBody(createMain());
    }

    protected abstract SpacetimeMenu createMenu();
    protected abstract SpaceTimeBody createMain();


}
