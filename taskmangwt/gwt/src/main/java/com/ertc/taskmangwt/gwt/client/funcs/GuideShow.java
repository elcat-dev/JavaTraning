package com.ertc.taskmangwt.gwt.client.funcs;

import com.ertc.taskmangwt.gwt.client.widgets.AddTaskFormWidget;
import com.ertc.taskmangwt.gwt.client.widgets.TaskCangeFormWidget;
import com.ertc.taskmangwt.gwt.client.widgets.TaskInfoFormWidget;
import com.google.gwt.user.client.ui.Composite;

import java.util.*;

public class GuideShow {
    private static GuideShow guideShow;
    private List<Composite> objectList;

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

}
