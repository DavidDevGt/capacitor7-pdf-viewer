package com.daviddevgt.plugins.pdfviewer;

import android.content.Context;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.rajat.pdfviewer.PdfViewerActivity;
import com.rajat.pdfviewer.util.saveTo;
import java.util.Collections;

@CapacitorPlugin(name = "PDFViewer")
public class PDFViewerPlugin extends Plugin {

    private PDFViewer implementation = new PDFViewer();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    /**
     * Abre un PDF desde una URL usando la librería Pdf-Viewer en su versión 2.x+.
     * Ajusta los parámetros (pdfTitle, saveTo, enableDownload) según tu preferencia.
     */
    @PluginMethod
    public void openPDF(PluginCall call) {
        String url = call.getString("url");
        if (url == null) {
            call.reject("No URL Provided");
            return;
        }

        try {
            PdfViewerActivity.Companion.launchPdfFromUrl(
                getActivity(),
                url,
                "PDF Reader",
                saveTo.ASK_EVERYTIME,
                false,
                Collections.emptyMap()
            );
            call.resolve();
        } catch (Exception e) {
            call.reject("Error opening PDF: " + e.getMessage());
        }
    }
}
