package fm.qingting.qtradio.model;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MutiCheckManageableAdapter extends CustomizedAdapter
{
  protected int mCheckOffset = 0;
  private final HashSet<Integer> mCheckPositions = new HashSet();
  protected boolean showDeleteButton = false;

  public MutiCheckManageableAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  private void dispatchStateChanged(boolean paramBoolean)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(null, "stateChanged", Boolean.valueOf(paramBoolean));
  }

  public void checkAll()
  {
    if (this.mCheckPositions.size() == 0)
      dispatchStateChanged(true);
    for (int i = 0; i < getCount(); i++)
      this.mCheckPositions.add(Integer.valueOf(i));
    notifyDataSetChanged();
  }

  public void checkIndex(int paramInt)
  {
    if (this.mCheckPositions.contains(Integer.valueOf(paramInt)))
    {
      this.mCheckPositions.remove(Integer.valueOf(paramInt));
      if (this.mCheckPositions.size() == 0)
        dispatchStateChanged(false);
    }
    while (true)
    {
      notifyDataSetChanged();
      return;
      if (this.mCheckPositions.size() == 0)
        dispatchStateChanged(true);
      this.mCheckPositions.add(Integer.valueOf(paramInt));
    }
  }

  public Iterator<Integer> getCheckList()
  {
    return this.mCheckPositions.iterator();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    IView localIView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    localIView.update("checkstate", Boolean.valueOf(isChecked(paramInt)));
    if (this.showDeleteButton)
      localIView.update("showManage", Integer.valueOf(this.mCheckOffset));
    while (true)
    {
      return localIView.getView();
      localIView.update("hideManage", null);
    }
  }

  public boolean hasCheckedIndexs()
  {
    return this.mCheckPositions.size() > 0;
  }

  public void hideManage()
  {
    this.showDeleteButton = false;
    notifyDataSetChanged();
  }

  public void init(int paramInt)
  {
    notifyDataSetChanged();
  }

  protected boolean isChecked(int paramInt)
  {
    return this.mCheckPositions.contains(Integer.valueOf(paramInt));
  }

  public void resetCheck()
  {
    if (this.mCheckPositions.size() > 0)
    {
      this.mCheckPositions.clear();
      notifyDataSetChanged();
      dispatchStateChanged(false);
    }
  }

  public void showManage(int paramInt)
  {
    this.showDeleteButton = true;
    this.mCheckOffset = paramInt;
    notifyDataSetChanged();
  }
}

/* Location:           /Users/zhangxun-xy/Downloads/qingting2/classes_dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.MutiCheckManageableAdapter
 * JD-Core Version:    0.6.2
 */