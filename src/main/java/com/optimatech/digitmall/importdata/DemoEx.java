package com.optimatech.digitmall.importdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DemoEx {
    private HashSet<Object> data;

    public DemoEx(HashSet<Object> set){
        this.data = set;
    }
}
