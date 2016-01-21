Animated ExpandableListView
====================

### A detailed explanation for how this class works
Animating the ExpandableListView was no easy task. The way that this
class does it is by exploiting how an ExpandableListView works.

Normally when {@link ExpandableListView#collapseGroup(int)} or
{@link ExpandableListView#expandGroup(int)} is called, the view toggles
the flag for a group and calls notifyDataSetChanged to cause the ListView
to refresh all of it's view. This time however, depending on whether a
group is expanded or collapsed, certain childViews will either be ignored
or added to the list.

Knowing this, we can come up with a way to animate our views. For
instance for group expansion, we tell the adapter to animate the
children of a certain group. We then expand the group which causes the
ExpandableListView to refresh all views on screen. The way that
ExpandableListView does this is by calling getView() in the adapter.
However since the adapter knows that we are animating a certain group,
instead of returning the real views for the children of the group being
animated, it will return a fake dummy view. This dummy view will then
draw the real child views within it's dispatchDraw function. The reason
we do this is so that we can animate all of it's children by simply
animating the dummy view. After we complete the animation, we tell the
adapter to stop animating the group and call notifyDataSetChanged. Now
the ExpandableListView is forced to refresh it's views again, except this
time, it will get the real views for the expanded group.

So, to list it all out, when {@link #expandGroupWithAnimation(int)} is
called the following happens:

1. The ExpandableListView tells the adapter to animate a certain group.
2. The ExpandableListView calls expandGroup.
3. ExpandGroup calls notifyDataSetChanged.
4. As an result, getChildView is called for expanding group.
5. Since the adapter is in "animating mode", it will return a dummy view.
6. This dummy view draws the actual children of the expanding group.
7. This dummy view's height is animated from 0 to it's expanded height.
8. Once the animation completes, the adapter is notified to stop
   animating the group and notifyDataSetChanged is called again.
9. This forces the ExpandableListView to refresh all of it's views again.
10.This time when getChildView is called, it will return the actual
   child views.

For animating the collapse of a group is a bit more difficult since we
can't call collapseGroup from the start as it would just ignore the
child items, giving up no chance to do any sort of animation. Instead
what we have to do is play the animation first and call collapseGroup
after the animation is done.

So, to list it all out, when {@link #collapseGroupWithAnimation(int)} is
called the following happens:

1. The ExpandableListView tells the adapter to animate a certain group.
2. The ExpandableListView calls notifyDataSetChanged.
3. As an result, getChildView is called for expanding group.
4. Since the adapter is in "animating mode", it will return a dummy view.
5. This dummy view draws the actual children of the expanding group.
6. This dummy view's height is animated from it's current height to 0.
7. Once the animation completes, the adapter is notified to stop
   animating the group and notifyDataSetChanged is called again.
8. collapseGroup is finally called.
9. This forces the ExpandableListView to refresh all of it's views again.
10.This time when the ListView will not get any of the child views for
   the collapsed group.

### How to use
Just copy AnimatedExpandableListView in your project and the lis layouts (fragment_list_view, group_item, list_item) and use it as any native ExpandableListView.

You can see the example for more info
