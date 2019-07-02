package org.my.oa.sys.dto;

import java.util.ArrayList;
import java.util.List;

import org.my.oa.sys.domain.Module;

public class UserModule {
    
    /** 一级模块 */
    private Module firstModule;
    /** 二级模块 */
    private List<Module> secondModules = new ArrayList<>();

    public Module getFirstModule() {
        return firstModule;
    }

    public void setFirstModule(Module firstModule) {
        this.firstModule = firstModule;
    }

    public List<Module> getSecondModules() {
        return secondModules;
    }

    public void setSecondModules(List<Module> secondModules) {
        this.secondModules = secondModules;
    }

}
