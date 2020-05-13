package com.hl5u4v.progtech.core;

import com.hl5u4v.progtech.core.helpers.List2;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RouterTest {

    private static Router router;
    private static int endpointHit = 0;

    @BeforeAll
    static void setUp() {
        router = new Router();
    }

    @Test
    @Order(1)
    void registerRoute() {
        router.registerRoute("test route", this::routeEmptyParam);
        router.registerRoute("test ??", this::routeSingleParam);
        router.registerRoute("test ?? ??", this::routeDoubleParam);

        assertNotNull(router);
    }

    @Test
    @Order(2)
    void getRoutes() {
        var x = router.getRoutes();

        assertEquals(3, x.size());
    }

    @Test
    @Order(3)
    void call() {
        router.call("test route");
        router.call("TEST ROUTE");

        router.call("test 12");
        router.call("test asd");

        router.call("test 12 45");
        router.call("test asd 13");

        assertEquals(6, endpointHit);
    }

    private void routeDoubleParam(@NotNull List2<String> strings) {
        assertEquals(2, strings.size());
        endpointHit++;
    }

    private void routeSingleParam(@NotNull List2<String> strings) {
        assertEquals(1, strings.size());
        endpointHit++;
    }

    private void routeEmptyParam(@NotNull List2<String> x) {
        assertEquals(0, x.size());
        endpointHit++;
    }
}