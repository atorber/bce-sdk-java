/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca;

import com.baidubce.services.vca.model.AnalyzeCancelRequest;
import com.baidubce.services.vca.model.AnalyzeCancelResponse;
import org.apache.commons.lang3.StringUtils;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.vca.model.AnalyzeResponse;
import com.baidubce.services.vca.model.McaEmptyRequest;
import com.baidubce.services.vca.model.McaEmptyResponse;
import com.baidubce.services.vca.model.HighlightAnalyzeRequest;
import com.baidubce.services.vca.model.ImageAnalyzeResponse;
import com.baidubce.services.vca.model.QueryResultRequest;
import com.baidubce.services.vca.model.QueryResultResponse;
import com.baidubce.services.vca.model.AnalyzeRequest;
import com.baidubce.services.vca.model.QuerySubTaskRequest;
import com.baidubce.services.vca.model.QuerySubTaskResponse;
import com.baidubce.services.vca.model.StreamAnalyzeRequest;
import com.baidubce.services.vca.model.StreamAnalyzeResponse;
import com.baidubce.services.vca.model.StreamStopResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Vca client.
 *
 * @author houshunwei
 */
public class VcaClient extends AbstractBceClient {

    private static final String VERSION = "v2";
    private static final String VERSION_1 = "v1";
    private static final String MEDIA = "media";
    private static final String STREAM = "stream";
    private static final String IMAGE = "image";
    private static final String COVER = "cover";
    private static final String HIGHLIGHT = "highlight";
    private static final String ABSTRACT = "abstract";

    private static HttpResponseHandler[] vcaHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public VcaClient() {
        this(new BceClientConfiguration());
    }

    public VcaClient(BceClientConfiguration config) {
        super(config, vcaHandlers);
    }

    /**
     * Initiate media analyze for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return Analyze response.
     */
    public AnalyzeResponse analyze(String source) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        return analyze(request);
    }

    /**
     * Initiate media analyze for specified source and title.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @param title Media title.
     * @return Analyze response.
     */
    public AnalyzeResponse analyze(String source, String title) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        request.setTitle(title);
        return analyze(request);
    }

    /**
     * Initiate media analyze for specified source.
     *
     * @param request Analyze request, including media source path.
     * @return Analyze response.
     */
    public AnalyzeResponse analyze(AnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.PUT,
                request, MEDIA);
        return this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
    }

    /**
     * Cancel media analyze for specified source.
     *
     * @param request Cancel request, including media source path.
     * @return  response.
     */
    public AnalyzeCancelResponse cancel(AnalyzeCancelRequest request) {
        // base request
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.PUT,
                request, MEDIA);
        HashMap<String, String> params = new HashMap<>();
        params.put("source", request.getSource());
        params.put("cancel", null);
        internalRequest.setParameters(params);
        return this.invokeHttpClient(internalRequest, AnalyzeCancelResponse.class);
    }

    /**
     * Cancel media analyze for specified source.
     *
     * @return
     */
    public AnalyzeCancelResponse cancel(String source) {
        AnalyzeCancelRequest request = new AnalyzeCancelRequest();
        request.setSource(source);
        return cancel(request);
    }

    /**
     * Initiate image analyze for specified AnalyzeRequest and request image sync-interface.
     *
     * @param request Analyze request, including image source path.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public StreamAnalyzeResponse analyzeStream(StreamAnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.PUT,
                request, STREAM);
        return this.invokeHttpClient(internalRequest, StreamAnalyzeResponse.class);
    }

    public StreamAnalyzeResponse queryStream(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.withMediaSource(source);
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.GET, request, STREAM);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, StreamAnalyzeResponse.class);
    }

    public void stopStream(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.withMediaSource(source);
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.PUT,
                request, STREAM);
        internalRequest.addParameter("stop", StringUtils.EMPTY);
        this.invokeHttpClient(internalRequest, StreamStopResponse.class);
    }

    /**
     * Initiate cover analyze for specified source.
     *
     * @param source Video source path, supporting BOS, HTTP(S) URL.
     * @return AnalyzeResponse with analyze results.
     */

    public AnalyzeResponse analyzeCover(String source) {
        AnalyzeRequest request =new AnalyzeRequest();
        request.setSource(source);
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.PUT,
                request, COVER);
        return this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
    }

    /**
     * Initiate highlight analyze for specified source.
     *
     * @param source Video source path, supporting BOS, HTTP(S) URL.
     * @return AnalyzeResponse with analyze results.
     */

    public AnalyzeResponse analyzeHighlight(String source) {
        HighlightAnalyzeRequest request = new HighlightAnalyzeRequest();
        request.setSource(source);
        return analyzeHighlight(request);
    }

    /**
     * Initiate highlight analyze for specified source.
     *
     * @param request highlight analyze request.
     * @return AnalyzeResponse with analyze results.
     */

    public AnalyzeResponse analyzeHighlight(HighlightAnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.PUT,
                request, HIGHLIGHT);
        return this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
    }

    /**
     * Initiate image analyze for specified source.
     *
     * @param source image source path, supporting BOS, HTTP(S) URL.
     * @return ImageAnalyzeResponse with analyze results.
     */

    public ImageAnalyzeResponse analyzeImage(String source) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        return analyzeImage(request);
    }

    /**
     * Initiate image analyze for specified source and title.
     *
     * @param source image source path, supporting BOS, HTTP(S) URL.
     * @param title image title.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public ImageAnalyzeResponse analyzeImage(String source, String title) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        request.setTitle(title);
        return analyzeImage(request);
    }

    /**
     * Initiate image analyze for specified AnalyzeRequest and request image sync-interface.
     *
     * @param request Analyze request, including image source path.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public ImageAnalyzeResponse analyzeImage(AnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.PUT,
                request, IMAGE);
        internalRequest.addParameter("sync", "");
        return this.invokeHttpClient(internalRequest, ImageAnalyzeResponse.class);
    }


    /**
     * Initiate image abstract analyze for specified source.
     *
     * @param source image source path, supporting BOS, HTTP(S) URL.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public ImageAnalyzeResponse analyzeImageAbstract(String source) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        return analyzeImageAbstract(request);
    }


    /**
     * Initiate image analyze for specified AnalyzeRequest and request image sync-interface.
     *
     * @param request Analyze request, including image source path.
     * @return ImageAnalyzeResponse with analyze results.
     */
    public ImageAnalyzeResponse analyzeImageAbstract(AnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.PUT,
                request, ABSTRACT);
        return this.invokeHttpClient(internalRequest, ImageAnalyzeResponse.class);
    }


    /**
     * Query analyze result for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return Analyze result.
     */
    public QueryResultResponse queryImageAbstractResult(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.setSource(source);
        return queryImageAbstractResult(request);
    }

    /**
     * Query analyze result for specified source.
     *
     * @param request Query request, including media source path.
     * @return Analyze result.
     */
    public QueryResultResponse queryImageAbstractResult(QueryResultRequest request) {
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.GET,
                request, ABSTRACT);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QueryResultResponse.class);
    }


    /**
     * Query analyze result for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return Analyze result.
     */
    public QueryResultResponse queryResult(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.setSource(source);
        return queryResult(request);
    }

    /**
     * Query analyze result for specified source.
     *
     * @param request Query request, including media source path.
     * @return Analyze result.
     */
    public QueryResultResponse queryResult(QueryResultRequest request) {
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.GET,
                request, MEDIA);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QueryResultResponse.class);
    }

    /**
     * Query cover result for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return cover result.
     */
    public QueryResultResponse queryCoverResult(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.setSource(source);
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.GET,
                request, COVER);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QueryResultResponse.class);
    }

    /**
     * Query highlight result for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return highlight result.
     */
    public QueryResultResponse queryHighlightResult(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.setSource(source);
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.GET,
                request, HIGHLIGHT);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QueryResultResponse.class);
    }

    /**
     * Cancel highlight result for specified source.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @return highlight cancel result.
     */
    public McaEmptyResponse cancelHighlightResult(String source) {
        InternalRequest internalRequest = createRequest(VERSION_1, HttpMethodName.PUT,
                new McaEmptyRequest(), HIGHLIGHT);
        internalRequest.addParameter("source", source);
        internalRequest.addParameter("cancel", "");
        return this.invokeHttpClient(internalRequest, McaEmptyResponse.class);
    }

    /**
     * Query sub task result for specified source of directed type.
     *
     * @param source Media source path, supporting BOS, VOD, HTTP(S) URL.
     * @param type Sub task type.
     * @return Analyze result of sub task type.
     */
    public QuerySubTaskResponse querySubTask(String source, String type) {
        QuerySubTaskRequest request = new QuerySubTaskRequest();
        request.setSource(source);
        request.setSubTaskType(type);
        return querySubTask(request);
    }

    /**
     * Query sub task result for specified source of directed type.
     *
     * @param request Query request of sub task, including media source path and sub task type.
     * @return Analyze result of sub task type.
     */
    public QuerySubTaskResponse querySubTask(QuerySubTaskRequest request) {
        InternalRequest internalRequest = createRequest(VERSION, HttpMethodName.GET,
                request, MEDIA, request.getSubTaskType());
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QuerySubTaskResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path,
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     *
     *
     * @param version
     * @param httpMethod The HTTP method to use when sending the request.
     * @param request The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     * ready for callers to populate any additional headers or
     * parameters, and execute.
     */
    private InternalRequest createRequest(
            String version, HttpMethodName httpMethod, AbstractBceRequest request, String... pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(version);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }
}
