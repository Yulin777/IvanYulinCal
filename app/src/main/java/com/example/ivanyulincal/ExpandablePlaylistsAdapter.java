package com.example.ivanyulincal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import org.json.JSONObject;

class ExpandablePlaylistsAdapter extends BaseExpandableListAdapter {
    private MetadataJsonParser metadataJsonParser;
    private Presenter mPresenter;

    ExpandablePlaylistsAdapter(Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    void setData(JSONObject jsonObject) throws Exception {
        metadataJsonParser = new MetadataJsonParser(jsonObject);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if (metadataJsonParser != null) {
            try {
                return metadataJsonParser.getPlaylistsCount();
            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
                return 0;
            }
        }

        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (metadataJsonParser != null) {
            try {
                return metadataJsonParser.getVideosCount(groupPosition);
            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
                return 0;
            }
        }

        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (metadataJsonParser != null) {
            try {
                return metadataJsonParser.getPlaylist(groupPosition);
            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
                return null;
            }
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (metadataJsonParser != null) {
            try {
                return metadataJsonParser.getVideo(groupPosition, childPosition);
            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
                return null;
            }
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (metadataJsonParser != null) {
            try {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
                }
                ItemSetter holder = new ItemSetter(convertView);
                holder.setViews((JSONObject) getGroup(groupPosition));

            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
            }
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (metadataJsonParser != null) {
            try {
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false);
                    convertView.setPadding(100, 0, 0, 0); //used as a divider between parent and children
                }
                ItemSetter holder = new ItemSetter(convertView);
                holder.setViews((JSONObject) getChild(groupPosition, childPosition));
                holder.setChildClick(mPresenter);
            } catch (Exception e) {
                e.printStackTrace();
                mPresenter.onError();
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
