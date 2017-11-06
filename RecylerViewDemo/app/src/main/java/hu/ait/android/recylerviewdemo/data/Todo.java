package hu.ait.android.recylerviewdemo.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Todo extends RealmObject {

    @PrimaryKey
    private String todoID;

    private String todoTitle;
    private String createDate;
    private boolean done;


    public Todo() {

    }

    public Todo(String todoTitle, String createDate, boolean done) {
        this.todoTitle = todoTitle;
        this.createDate = createDate;
        this.done = done;

    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public String getTodoID() {
        return todoID;
    }
}
