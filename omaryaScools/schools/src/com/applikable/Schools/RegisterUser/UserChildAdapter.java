package com.applikable.Schools.RegisterUser;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alhusban on 22/11/2015.
 */
public abstract class UserChildAdapter<T> extends BaseAdapter {

    private List<T> childList;

    public UserChildAdapter() {
        childList = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public T getItem(int i) {
        return childList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return GetView(i, view, viewGroup);
    }

    public abstract View GetView(int position, View view, ViewGroup viewGroup);


    public void AddItem(T item) {
        childList.add(item);
        this.notifyDataSetChanged();
    }

    public void AddAll(List<T> Collection) {
        for (T item : Collection) {
            childList.add(item);
        }
        this.notifyDataSetChanged();

    }

    public T RemoveItemAt(int position) {
        T item = childList.remove(position);
        notifyDataSetChanged();
        return item;
    }

    public void ClearList() {
        childList.clear();
        notifyDataSetChanged();
    }

    public List<T> getChildList() {
        return childList;
    }
}
