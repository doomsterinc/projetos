/*******************************************************************************
 *
 *    Copyright (c) Baina Info Tech Co. Ltd
 *
 *    HelloWorld
 *
 *    MyAddonService
 *    TODO File description or class description.
 *
 *    @author: dhu
 *    @since:  2012-5-21
 *    @version: 1.0
 *
 ******************************************************************************/
package com.dolphin.browser.extension.history;

import com.dolphin.browser.addons.AddonService;
import com.dolphin.browser.addons.AlertDialogBuilder;
import com.dolphin.browser.addons.Browser;
import com.dolphin.browser.addons.HistoryInfo;
import com.dolphin.browser.addons.IContentObserver;
import com.dolphin.browser.addons.ITab;
import com.dolphin.browser.addons.IWebView;
import com.dolphin.browser.addons.OnClickListener;
import com.dolphin.browser.addons.TitleBarAction;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;

/**
 * MyAddonService of HelloWorld.
 * @author dhu
 *
 */
public class MyAddonService extends AddonService {

    @Override
    protected void onBrowserConnected(Browser browser) {
        try {
            //Show add-on bar action
            browser.addonBarAction.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
            browser.addonBarAction.setTitle("visits");
            browser.addonBarAction.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(Browser browser) {
                    try {
                        String url = getCurrentUrl(browser);
                        if (TextUtils.isEmpty(url)) {
                            showDialog(browser, "Url of current page is empty");
                        } else {
                            int visits = getUrlVisits(browser, url);
                            showDialog(browser, "You have visited current page " + visits + " times.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            browser.addonBarAction.show();

            //Listen title bar url change event
            browser.titleBarAction.addListener(mTitleBarActionListener);

            //listen history change event
            browser.history.registerHistoryContentObserver(mHistoryContentObserver);

            updateBadgeNumber(browser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentUrl(Browser browser) throws RemoteException {
        //Get the url of current web page
        ITab tab = browser.tabs.getCurrent();
        if (null == tab) {
            return null;
        }
        IWebView webView = tab.getWebView();
        if (null == webView) {
            return null;
        }
        return webView.getUrl();
    }

    private int getUrlVisits(Browser browser, String url) throws RemoteException {
        //Get visits of url from history
        List<HistoryInfo> historyInfos = browser.history.searchHistories(HistoryInfo.KEY_URL + "=?", new String[]{url});
        if (historyInfos != null && historyInfos.size() > 0) {
            return historyInfos.get(0).visits;
        }
        return 0;
    }

    private void showDialog(Browser browser, String message) {
        //show dialog
        AlertDialogBuilder builder = new AlertDialogBuilder();
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(message);
        builder.setPositiveButton("Close", (Message)null);
        try {
            browser.window.showDialog(builder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TitleBarAction.Listener mTitleBarActionListener = new TitleBarAction.Listener() {

        @Override
        public void onUrlChanged(String newUrl) {
            updateBadgeNumber(getCallingBrowser());
        }
    };

    private IContentObserver mHistoryContentObserver = new IContentObserver.Stub() {

        @Override
        public void onChange() throws RemoteException {
            updateBadgeNumber(getCallingBrowser());
        }
    };

    @Override
    protected void onBrowserDisconnected(Browser browser) {
    }

    //Update badge number of add-on bar icon
    protected void updateBadgeNumber(Browser browser) {
        try {
            String url = getCurrentUrl(browser);
            if (TextUtils.isEmpty(url)) {
                browser.addonBarAction.setIconBadgeNumber(0);
            } else {
                int visits = getUrlVisits(browser, url);
                browser.addonBarAction.setIconBadgeNumber(visits);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
