package org.spacetime;


import com.vaadin.server.Page;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 19/10/16.
 */
public class SpaceTimeView extends VerticalLayout implements Page.BrowserWindowResizeListener {
    private Logo logo;
    private HorizontalLayout base;
    private ServicePortal sessionTypesPortal;
    private ServicePortal mathemathicsPortal;
    private ServicePortal blogPortal;

    public SpaceTimeView() {
        setStyleName("spacetime");
        initLogo();
        initBase();
        initMathematicsPortal();
        initSessionTypesPortal();
        initBlogPortal();
        base.addComponents(mathemathicsPortal, sessionTypesPortal);
        addComponents(logo, base);
        setSizeFull();
        setExpandRatio(logo, .1f);
        setExpandRatio(base, .9f);
    }

    private void initBase() {
        base = new HorizontalLayout();
        base.setSizeFull();
    }

    private void initLogo() {
        logo = new Logo();
    }


    private void initSessionTypesPortal() {
        sessionTypesPortal = new ServicePortal("Session Types: Specification Driven Development", getDummyDescription2());
    }

    private void initMathematicsPortal() {
        mathemathicsPortal = new ServicePortal("Mathematics", getDummyDescription1());
        mathemathicsPortal.addService(new ServicePortal("+"));
        mathemathicsPortal.addService(new ServicePortal("-"));
        mathemathicsPortal.addService(new ServicePortal("x"));
        mathemathicsPortal.addService(new ServicePortal("/"));
    }

    private void initBlogPortal() {
        blogPortal = new ServicePortal("Blog", getDummyDescription1());
    }


    private String getDummyDescription1() {
        return "Lorem ipsum dolor sit amet, nunc pellentesque in aliquet " +
                "parturient nonummy quos, blandit nascetur, amet ante mauris " +
                "lacus ut viverra, ut quis dictumst aliquam arcu mauris egestas. " +
                "Libero nunc, pellentesque tincidunt amet convallis tristique aliquam, " +
                "turpis molestie in. Augue at. Est augue massa arcu phasellus risus, " +
                "lobortis a id vel convallis sed amet. Scelerisque molestie ante curabitur " +
                "morbi tincidunt. Proin sem libero enim leo vivamus faucibus, scelerisque adipisci, " +
                "at pellentesque massa vel ullamcorper imperdiet magna.";
    }

    private String getDummyDescription2() {
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

    @Override
    public void browserWindowResized(Page.BrowserWindowResizeEvent browserWindowResizeEvent) {
        setWidth(browserWindowResizeEvent.getWidth(), Unit.PIXELS);
        setHeight(browserWindowResizeEvent.getHeight(), Unit.PIXELS);
    }
}
