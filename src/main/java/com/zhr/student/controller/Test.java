package com.zhr.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<A> aList = new ArrayList<>();
        Map<String, String> bMap = new HashMap<>();
        aList.add(new A("1","Mary"));
        aList.add(new A("2","Jack"));
        bMap.put("1","Carry");
        bMap.put("3","Lily");

        /*aList.forEach(a -> {
            bList.forEach(b -> {
                if (a.getId().equals(b.getId())) {
                    a.setName(b.getName());
                }
            });
        });*/
        for (A a: aList
        ) {
            System.out.println(a.getId() + " --- " + a.getName());
        }

        aList.forEach(a -> {
            String name = bMap.get(a.getId());
            if (name != null) {
                a.setName(name);
            }
        });

        for (A a: aList
             ) {
            System.out.println(a.getId() + " --- " + a.getName());
        }
    }

    private static A compare(A a, List<B> bList) {
        bList.forEach(b -> {
            if (a.getId().equals(b.getId())) {
                a.setName(b.getName());
            }
        });
        return a;
    }


}

class A {
    private String id;
    private String name;

    public A(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class B {
    private String id;
    private String name;

    public B(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
