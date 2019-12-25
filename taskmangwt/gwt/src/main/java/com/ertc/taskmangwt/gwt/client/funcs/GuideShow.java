package com.ertc.taskmangwt.gwt.client.funcs;

import com.ertc.taskmangwt.gwt.client.widgets.AddTaskFormWidget;
import com.ertc.taskmangwt.gwt.client.widgets.TaskCangeFormWidget;
import com.ertc.taskmangwt.gwt.client.widgets.TaskInfoFormWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;

import java.util.*;

public class GuideShow {
    private static GuideShow guideShow;
    private List<Composite> objectList;
    private TabLayoutPanel tabPanel;

    private GuideShow(){
        objectList = new ArrayList<Composite>();
    }

    public static GuideShow getGuideShow(){
        synchronized (GuideShow.class) {
            if (guideShow == null) {
                guideShow = new GuideShow();
            }
        }
        return guideShow;
    }

    public void setObjects(
            TaskCangeFormWidget taskCangeFormWidget
            , TaskInfoFormWidget taskInfoFormWidget
            , AddTaskFormWidget addTaskFormWidget
    ){
        objectList.add(taskCangeFormWidget);
        objectList.add(taskInfoFormWidget);
        objectList.add(addTaskFormWidget);
    }

    public void setTabPanel(TabLayoutPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    public void itsVisible(Composite inObject){
        for (Composite curObject: objectList) {
            if(curObject.equals(inObject)){
                inObject.setVisible(true);
            }
            else {
                curObject.setVisible(false);
            }
        }
    }

    public void switchTab(){
        if(tabPanel.getSelectedIndex() == 0){
            tabPanel.selectTab(1);
        }
        else{
            tabPanel.selectTab(0);
        }
    }

    public void switchTabToLogin() {
        tabPanel.selectTab(0);
    }

}
