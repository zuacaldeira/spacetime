package org.spacetime.backend;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Generic tests for the CrudService
 */
public class CrudServiceTest {

    private CrudService<TestBean> service;

    @BeforeMethod
    public void setUp() {
        service = new CrudService<>();
    }

    @Test
    public void testSaveTenBeansToService() {
        for (int i = 0; i < 10; ++i) {
            Assert.assertEquals(service.findAll().size(), i, "Unexpected size of the service storage");
            TestBean entity = new TestBean();
            service.save(entity);
            Assert.assertTrue(service.findAll().contains(entity), "Storage did not contain new entity");
        }
    }

    @Test
    public void testRemoveBeanFromService() {
        int i = 0;
        while (i < 10) {
            TestBean entity = new TestBean();
            service.save(entity);
            ++i;
        }

        Assert.assertEquals(service.findAll().size(), i, "Initial size did not match");
        while (service.findAll().size() > 2) {
            TestBean toRemove = service.findAll().get(2);
            service.delete(toRemove);

            Assert.assertEquals(service.findAll().size(), --i, "Size did not match after remove");
            Assert.assertFalse(service.findAll().contains(toRemove), "Storage should not contain removed bean");
        }
    }

    @Test
    public void testAddEntityTwice() {
        TestBean entity = new TestBean();
        service.save(entity);
        Assert.assertEquals(service.findAll().size(), 1, "Unexpected size after store");
        service.save(entity);
        Assert.assertEquals(service.findAll().size(), 1, "Same bean should not be stored twice");
    }
}
