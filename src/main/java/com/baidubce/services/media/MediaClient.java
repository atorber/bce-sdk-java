/*
 * Copyright 2015-2020 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.media;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.baidubce.services.media.model.Area;
import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CreateJobRequest;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.CreateNotificationRequest;
import com.baidubce.services.media.model.CreateNotificationResponse;
import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreatePipelineResponse;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.CreatePresetResponse;
import com.baidubce.services.media.model.CreateSubtitleJobRequest;
import com.baidubce.services.media.model.CreateSubtitleJobResponse;
import com.baidubce.services.media.model.CreateThumbnailJobRequest;
import com.baidubce.services.media.model.CreateThumbnailJobResponse;
import com.baidubce.services.media.model.CreateThumbnailPresetRequest;
import com.baidubce.services.media.model.CreateThumbnailPresetResponse;
import com.baidubce.services.media.model.CreateTranscodingJobRequest;
import com.baidubce.services.media.model.CreateTranscodingJobResponse;
import com.baidubce.services.media.model.CreateWaterMarkRequest;
import com.baidubce.services.media.model.CreateWaterMarkResponse;
import com.baidubce.services.media.model.DeleteNotificationRequest;
import com.baidubce.services.media.model.DeleteNotificationResponse;
import com.baidubce.services.media.model.DeletePipelineRequest;
import com.baidubce.services.media.model.DeletePresetRequest;
import com.baidubce.services.media.model.DeletePresetResponse;
import com.baidubce.services.media.model.DeleteThumbnailPresetRequest;
import com.baidubce.services.media.model.DeleteWaterMarkRequest;
import com.baidubce.services.media.model.DeleteWaterMarkResponse;
import com.baidubce.services.media.model.Encryption;
import com.baidubce.services.media.model.ExtraCfg;
import com.baidubce.services.media.model.GetJobRequest;
import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.GetMediaInfoOfFileRequest;
import com.baidubce.services.media.model.GetMediaInfoOfFileResponse;
import com.baidubce.services.media.model.GetNotificationRequest;
import com.baidubce.services.media.model.GetNotificationResponse;
import com.baidubce.services.media.model.GetPipelineRequest;
import com.baidubce.services.media.model.GetPipelineResponse;
import com.baidubce.services.media.model.GetPresetRequest;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.GetSubtitleJobRequest;
import com.baidubce.services.media.model.GetSubtitleJobResponse;
import com.baidubce.services.media.model.GetThumbnailJobRequest;
import com.baidubce.services.media.model.GetThumbnailJobResponse;
import com.baidubce.services.media.model.GetThumbnailPresetRequest;
import com.baidubce.services.media.model.GetThumbnailPresetResponse;
import com.baidubce.services.media.model.GetTranscodingEncryptionKeyRequest;
import com.baidubce.services.media.model.GetTranscodingEncryptionKeyResponse;
import com.baidubce.services.media.model.GetTranscodingJobRequest;
import com.baidubce.services.media.model.GetTranscodingJobResponse;
import com.baidubce.services.media.model.GetWaterMarkRequest;
import com.baidubce.services.media.model.GetWaterMarkResponse;
import com.baidubce.services.media.model.Insert;
import com.baidubce.services.media.model.ListJobsRequest;
import com.baidubce.services.media.model.ListJobsResponse;
import com.baidubce.services.media.model.ListNotificationsRequest;
import com.baidubce.services.media.model.ListNotificationsResponse;
import com.baidubce.services.media.model.ListPipelinesRequest;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.ListPresetsRequest;
import com.baidubce.services.media.model.ListPresetsResponse;
import com.baidubce.services.media.model.ListSubtitleJobsRequest;
import com.baidubce.services.media.model.ListSubtitleJobsResponse;
import com.baidubce.services.media.model.ListThumbnailJobsRequest;
import com.baidubce.services.media.model.ListThumbnailJobsResponse;
import com.baidubce.services.media.model.ListThumbnailPresetsRequest;
import com.baidubce.services.media.model.ListThumbnailPresetsResponse;
import com.baidubce.services.media.model.ListTranscodingJobsRequest;
import com.baidubce.services.media.model.ListTranscodingJobsResponse;
import com.baidubce.services.media.model.ListWaterMarkRequest;
import com.baidubce.services.media.model.ListWaterMarkResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.Source;
import com.baidubce.services.media.model.SourceClip;
import com.baidubce.services.media.model.SubtitleSource;
import com.baidubce.services.media.model.SubtitleTarget;
import com.baidubce.services.media.model.Target;
import com.baidubce.services.media.model.ThumbnailCapture;
import com.baidubce.services.media.model.ThumbnailPresetCapture;
import com.baidubce.services.media.model.ThumbnailPresetTarget;
import com.baidubce.services.media.model.ThumbnailSource;
import com.baidubce.services.media.model.ThumbnailTarget;
import com.baidubce.services.media.model.Timeline;
import com.baidubce.services.media.model.TransCfg;
import com.baidubce.services.media.model.UpdatePipelineRequest;
import com.baidubce.services.media.model.UpdatePipelineResponse;
import com.baidubce.services.media.model.UpdatePresetRequest;
import com.baidubce.services.media.model.UpdatePresetResponse;
import com.baidubce.services.media.model.UpdateThumbnailPresetRequest;
import com.baidubce.services.media.model.UpdateThumbnailPresetResponse;
import com.baidubce.services.media.model.Video;
import com.baidubce.services.media.model.Watermarks;

import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;


/**
 * Client for accessing Media Transcoder Services. All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 * Created by xuchuan on 2015/4/20.
 */
public class MediaClient extends AbstractBceClient {

    /**
     * The version information for Media service APIs as URI prefix.
     */
    private static final String VERSION = "v3";

    /**
     * The common URI prefix for job services.
     */
    private static final String TRANSCODE_JOB = "job/transcoding";

    /**
     * The common URI prefix for pipeline services.
     */
    private static final String PIPELINE = "pipeline";

    /**
     * The common URI prefix for preset services.
     */
    private static final String PRESET = "preset";
    
    /**
     * The common URI prefix for thumbnail preset services.
     */
    private static final String THUMBNAIL_PRESET = "preset/thumbnail";

    /**
     * The common URI prefix for media-info services.
     */
    private static final String MEDIAINFO = "mediainfo";

    /**
     * The common URI prefix for water mark services.
     */
    private static final String WATER_MARK = "watermark";

    /**
     * The common URI prefix for thumbnail services.
     */
    private static final String THUMBNAIL = "job/thumbnail";

    /**
     * The common URI prefix for subtitle services.
     */
    private static final String SUBTITLE = "job/subtitle";

    /**
     * The common URI prefix for subtitle services.
     */
    private static final String TRANSCODING_KEY = "transcoding/key";

    /**
     * The common URI prefix for notification services.
     */
    private static final String NOTIFICATION = "notification";

    /**
     * The default capacity of a new pipeline.
     */
    private static final int DEFAULT_PIPELINE_CAPACITY = 20;

    /**
     * Exception messages.
     */
    public static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    public static final String PIPELINENAME_MESSAGE_KEY = "pipelineName";
    public static final String SOURCE_MESSAGE_KEY = "source";
    public static final String TARGET_MESSAGE_KEY = "target";
    public static final String SOURCEKEY_MESSAGE_KEY = "sourceKey";
    public static final String TARGETKEY_MESSAGE_KEY = "targetKey";
    public static final String SOURCEBUCKET_MESSAGE_KEY = "sourceBucket";
    public static final String TARGETBUCKET_MESSAGE_KEY = "targetBucket";
    public static final String NAME_MESSAGE_KEY = "name";
    public static final String ENDPOINT_MESSAGE_KEY = "endpoint";
    public static final String PRESETNAME_MESSAGE_KEY = "presetName";
    public static final String JOBID_MESSAGE_KEY = "jobId";
    public static final String BUCKET_MESSAGE_KEY = "bucket";
    public static final String KEY_MESSAGE_KEY = "key";
    public static final String WATERMARKID_MESSAGE_KEY = "watermarkId";
    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static final HttpResponseHandler[] mediaHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new MediaEncryptionKeyResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new Media client to invoke service methods on Media Transcoder.
     */
    public MediaClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access Media Transcoder services.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Media services (e.g. proxy settings, retry counts, etc).
     */
    public MediaClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, mediaHandlers);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param sourceKey    The key of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * 
     * @return The newly created job ID.
     * @deprecated As of release 0.8.5, replaced by {@link #createTranscodingJob(String, String, String, String)}
     */
    @Deprecated
    public CreateJobResponse createJob(String pipelineName, String sourceKey, String targetKey, String presetName) {
        CreateJobRequest request = new CreateJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(sourceKey);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(targetKey);
        target.setPresetName(presetName);
        request.setTarget(target);

        return createJob(request);
    }
    
    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param request The request object containing all options for creating a job.
     *     
     * @return The newly created job ID.
     * @deprecated As of release 0.8.5, replaced by {@link #createTranscodingJob(CreateTranscodingJobRequest)}}
     */
    @Deprecated
    public CreateJobResponse createJob(CreateJobRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        checkNotNull(request.getSource(), checkEmptyExceptionMessageFormat(SOURCE_MESSAGE_KEY));
        checkNotNull(request.getTarget(), checkEmptyExceptionMessageFormat(TARGET_MESSAGE_KEY));
        checkStringNotEmpty(request.getTarget().getTargetKey(),
                checkEmptyExceptionMessageFormat(TARGETKEY_MESSAGE_KEY));
        checkStringNotEmpty(request.getTarget().getPresetName(),
                checkEmptyExceptionMessageFormat(PRESETNAME_MESSAGE_KEY));
        
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, TRANSCODE_JOB);

        return invokeHttpClient(internalRequest, CreateJobResponse.class);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param pipelineName   The name of a pipeline.
     *     
     * @return The list of job IDs.
     * @deprecated As of release 0.8.5, replaced by {@link #listTranscodingJobs(String)}
     */
    @Deprecated
    public ListJobsResponse listJobs(String pipelineName) {
        ListJobsRequest request = new ListJobsRequest();
        request.setPipelineName(pipelineName);
        return listJobs(request);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param request The request object containing all options for list jobs.
     *     
     * @return The list of job IDs.
     * @deprecated As of release 0.8.5, replaced by {@link #listTranscodingJobs(ListTranscodingJobsRequest)}
     */
    @Deprecated
    public ListJobsResponse listJobs(ListJobsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB);
        internalRequest.addParameter(PIPELINENAME_MESSAGE_KEY, request.getPipelineName());
        return invokeHttpClient(internalRequest, ListJobsResponse.class);
    }
    
    /**
     * Retrieve the status of a job.
     *
     * @param jobId  The ID of a job.
     *     
     * @return The status of a job.
     * @deprecated As of release 0.8.5, replaced by {@link #getTranscodingJob(String)}
     */
    @Deprecated
    public GetJobResponse getJob(String jobId) {
        GetJobRequest request = new GetJobRequest();
        request.setJobId(jobId);
        return getJob(request);
    }

    /**
     * Retrieve the status of a job.
     *
     * @param request The request object containing all options for retrieving job status.
     *     
     * @return The status of a job.
     * @deprecated As of release 0.8.5, replaced by {@link #getTranscodingJob(GetTranscodingJobRequest)}
     */
    @Deprecated
    public GetJobResponse getJob(GetJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getJobId(),  checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB, request.getJobId());

        return invokeHttpClient(internalRequest, GetJobResponse.class);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param sourceKey    The key of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * 
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, String sourceKey, String targetKey, String presetName) {
        return createTranscodingJob(pipelineName, sourceKey, targetKey, presetName,
                null, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset, watermarkId, and
     * delogoArea.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param sourceKey    The key of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     * @param delogoArea   The delogo area (x, y, width, height).
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, String sourceKey, String targetKey, String presetName,
            String watermarkId, Area delogoArea) {
        CreateTranscodingJobRequest request = new CreateTranscodingJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        source.setSourceKey(sourceKey);
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(targetKey);
        target.setPresetName(presetName);
        if (!Strings.isNullOrEmpty(watermarkId)) {
            List<String> watermarkIds = Collections.singletonList(watermarkId);
            target.setWatermarkIds(watermarkIds);
        }
        if (delogoArea != null) {
            target.setDelogoArea(delogoArea);
        }
        request.setTarget(target);

        return createTranscodingJob(request);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName) {
        return createTranscodingJob(pipelineName, clips, targetKey, presetName,
                null, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset and watermarkId
     * associated with the job.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName,
            String watermarkId) {
        return createTranscodingJob(pipelineName, clips, targetKey, presetName,
                watermarkId, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset, watermarkId, and
     * delogoArea.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     * @param delogoArea   The delogo area (x, y, width, height).
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName,
            String watermarkId, Area delogoArea) {
        return createTranscodingJob(pipelineName, clips, targetKey, presetName,
                watermarkId, delogoArea, null, null);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset, watermarkId, and
     * delogoArea.
     *
     * @param pipelineName The name of pipeline used by this job.
     * @param clips    The keys of the source media file in the bucket specified in the pipeline.
     * @param targetKey    The key of the target media file in the bucket specified in the pipeline.
     * @param presetName   The name of the preset used by this job.
     * @param watermarkId  Single watermarkId associated with the job.
     * @param delogoArea   The delogo area (x, y, width, height).
     * @param crop   The crop area (x, y, width, height).
     * @param inserts   The list of Insert.
     *
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(
            String pipelineName, List<SourceClip> clips, String targetKey, String presetName,
            String watermarkId, Area delogoArea, Area crop, List<Insert> inserts) {
        CreateTranscodingJobRequest request = new CreateTranscodingJobRequest();
        request.setPipelineName(pipelineName);
        Source source = new Source();
        for (SourceClip clip : clips) {
            source.addClip(clip);
        }
        request.setSource(source);
        Target target = new Target();
        target.setTargetKey(targetKey);
        target.setPresetName(presetName);
        if (!Strings.isNullOrEmpty(watermarkId)) {
            List<String> watermarkIds = Collections.singletonList(watermarkId);
            target.setWatermarkIds(watermarkIds);
        }
        if (delogoArea != null) {
            target.setDelogoArea(delogoArea);
        }
        if (crop != null) {
            target.setCrop(crop);
        }
        if (inserts != null) {
            target.setInserts(inserts);
        }
        request.setTarget(target);

        return createTranscodingJob(request);
    }

    /**
     * Creates a new transcoder job which converts media files in BOS buckets with specified preset.
     *
     * @param request The request object containing all options for creating a job.
     *     
     * @return The newly created job ID.
     */
    public CreateTranscodingJobResponse createTranscodingJob(CreateTranscodingJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        checkNotNull(request.getSource(), checkEmptyExceptionMessageFormat(SOURCE_MESSAGE_KEY));
        checkNotNull(request.getTarget(), checkEmptyExceptionMessageFormat(TARGET_MESSAGE_KEY));
        checkStringNotEmpty(request.getTarget().getTargetKey(),
                checkEmptyExceptionMessageFormat(TARGETKEY_MESSAGE_KEY));
        checkStringNotEmpty(request.getTarget().getPresetName(),
                checkEmptyExceptionMessageFormat(PRESETNAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, TRANSCODE_JOB);

        return invokeHttpClient(internalRequest, CreateTranscodingJobResponse.class);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param pipelineName   The name of a pipeline.
     *     
     * @return The list of job IDs.
     */
    public ListTranscodingJobsResponse listTranscodingJobs(String pipelineName) {
        ListTranscodingJobsRequest request = new ListTranscodingJobsRequest();
        request.setPipelineName(pipelineName);
        return listTranscodingJobs(request);
    }

    /**
     * List all transcoder jobs on specified pipeline.
     *
     * @param request The request object containing all options for list jobs.
     *     
     * @return The list of job IDs.
     */
    public ListTranscodingJobsResponse listTranscodingJobs(ListTranscodingJobsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB);
        internalRequest.addParameter(PIPELINENAME_MESSAGE_KEY, request.getPipelineName());
        return invokeHttpClient(internalRequest, ListTranscodingJobsResponse.class);
    }
    
    /**
     * Retrieve the status of a job.
     *
     * @param jobId  The ID of a job.
     *     
     * @return The status of a job.
     */
    public GetTranscodingJobResponse getTranscodingJob(String jobId) {
        GetTranscodingJobRequest request = new GetTranscodingJobRequest();
        request.setJobId(jobId);
        return getTranscodingJob(request);
    }

    /**
     * Retrieve the status of a job.
     *
     * @param request The request object containing all options for retrieving job status.
     *     
     * @return The status of a job.
     */
    public GetTranscodingJobResponse getTranscodingJob(GetTranscodingJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getJobId(),
                checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, TRANSCODE_JOB, request.getJobId());

        return invokeHttpClient(internalRequest, GetTranscodingJobResponse.class);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * @param capacity     The concurrent capability of the new pipeline.
     * 
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String sourceBucket, String targetBucket, int capacity) {
        return createPipeline(pipelineName, null, sourceBucket, targetBucket, capacity);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * 
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String sourceBucket, String targetBucket) {
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setDescription("");
        request.setSourceBucket(sourceBucket);
        request.setTargetBucket(targetBucket);
        return createPipeline(request);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of new pipeline.
     * @param description  The optional description of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * @param capacity     The concurrent capability of the new pipeline.
     *
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String description,  String sourceBucket, String targetBucket, int capacity) {
        return createPipeline(pipelineName, description, sourceBucket, targetBucket, capacity, null);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param pipelineName The name of new pipeline.
     * @param description  The optional description of the new pipeline.
     * @param sourceBucket The name of source bucket in Bos.
     * @param targetBucket The name of target bucket in Bos.
     * @param capacity     The concurrent capability of the new pipeline.
     * @param notification The name of notification
     * 
     */
    public CreatePipelineResponse createPipeline(
            String pipelineName, String description, String sourceBucket, String targetBucket, int capacity,
            String notification) {
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setDescription(description);
        request.setSourceBucket(sourceBucket);
        request.setTargetBucket(targetBucket);
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(capacity);
        config.setNotification(notification);
        request.setConfig(config);
        
        return createPipeline(request);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param request The request object containing all options for creating new pipeline.
     * 
     */
    public CreatePipelineResponse createPipeline(CreatePipelineRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getSourceBucket(),
                checkEmptyExceptionMessageFormat(SOURCEBUCKET_MESSAGE_KEY));
        checkStringNotEmpty(request.getTargetBucket(),
                checkEmptyExceptionMessageFormat(TARGETBUCKET_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, PIPELINE);
        
        return invokeHttpClient(internalRequest, CreatePipelineResponse.class);
    }

    /**
     * Creates a pipeline which enable you to perform multiple transcodes in parallel.
     *
     * @param request The request object containing all options for creating new pipeline.
     *
     */
    public UpdatePipelineResponse updatePipeline(UpdatePipelineRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getSourceBucket(),
                checkEmptyExceptionMessageFormat(SOURCEBUCKET_MESSAGE_KEY));
        checkStringNotEmpty(request.getTargetBucket(),
                checkEmptyExceptionMessageFormat(TARGETBUCKET_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request
                , PIPELINE, request.getPipelineName());

        return invokeHttpClient(internalRequest, UpdatePipelineResponse.class);
    }

    /**
     * List all your pipelines.
     *
     * @return The list of all your pipelines 
     */
    public ListPipelinesResponse listPipelines() {
        ListPipelinesRequest request = new ListPipelinesRequest();
        return listPipelines(request);
    }

    /**
     * List all your pipelines.
     *
     * @param request The request object containing all options for listing all pipelines.
     * 
     * @return The list of all your pipelines 
     */
    public ListPipelinesResponse listPipelines(ListPipelinesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PIPELINE);
        return invokeHttpClient(internalRequest, ListPipelinesResponse.class);
    }

    /**
     * Gets a pipeline with the specified pipeline name.
     *
     * @param pipelineName The name of your pipeline.
     * 
     * @return The information of your pipeline. 
     */
    public GetPipelineResponse getPipeline(String pipelineName) {
        GetPipelineRequest request = new GetPipelineRequest();
        request.setPipelineName(pipelineName);
        return getPipeline(request);
    }

    /**
     * Gets a pipeline with the specified pipeline name.
     *
     * @param request The request object containing all options for getting a pipelines.
     * 
     * @return The information of your pipeline. 
     */
    public GetPipelineResponse getPipeline(GetPipelineRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, PIPELINE, request.getPipelineName());

        return invokeHttpClient(internalRequest, GetPipelineResponse.class);
    }
    
    /**
     * Gets a pipeline with the specified pipeline name.
     *
     * @param pipelineName The name of your pipeline.
     *  
     */
    public void deletePipeline(String pipelineName) {
        DeletePipelineRequest request = new DeletePipelineRequest();
        request.setPipelineName(pipelineName);
        deletePipeline(request);
    }

    /**
     * Deletes a pipeline with the specified pipeline name.
     *
     * @param request The request object containing all options for deleting a pipelines.
     * 
     */
    public void deletePipeline(DeletePipelineRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, PIPELINE, request.getPipelineName());

        invokeHttpClient(internalRequest, CreatePipelineResponse.class);
    }

    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String container, Audio audio) {
        return createPreset(presetName, null, container, false, null, audio, null, null, null);
    }
        
    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String description, String container, Audio audio) {
        return createPreset(presetName, description, container, false, null, audio, null, null, null);
    }
        
    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param encryption  Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String container, Clip clip, Audio audio, Encryption encryption) {
        return createPreset(presetName, null, container, false, clip, audio, null, encryption, null);
    }
        
    /**
     * Create a preset which help to convert audio files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param encryption  Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Clip clip, Audio audio, Encryption encryption) {
        return createPreset(presetName, description, container, false, clip, audio, null, encryption, null);
    }
        
    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String container, Audio audio, Video video) {
        return createPreset(presetName, null, container, false, null, audio, video, null, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Audio audio, Video video) {
        return createPreset(presetName, description, container, false, null, audio, video, null, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName The name of the new preset.
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip The clip property of the preset.
     * @param audio Specify the audio format of target file.
     * @param video Specify the video format of target file.
     * @param encryption Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption) {
        return createPreset(presetName, null, container, false, clip, audio, video, encryption, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName The name of the new preset.
     * @param description The description of the new preset
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip The clip property of the preset.
     * @param audio Specify the audio format of target file.
     * @param video Specify the video format of target file.
     * @param encryption Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Clip clip, Audio audio, Video video,
            Encryption encryption) {
        return createPreset(presetName, description, container, false, clip, audio, video, encryption, null);
    }

    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarkId Specify the watermarkId.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String container, Clip clip, Audio audio, Video video, Encryption encryption,
            String watermarkId) {
        return createPreset(presetName, null, container, false, clip, audio, video, encryption, watermarkId);
    }
        
    /**
     * Create a preset which help to convert video files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarkId Specify the watermarkId.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, Clip clip, Audio audio, Video video,
            Encryption encryption, String watermarkId) {
        return createPreset(presetName, description, container, false, clip, audio, video, encryption, watermarkId);
    }
        
    /**
     * Create a preset which only convert source media file to a different container format without changing the file
     * contents.
     * 
     * @param presetName The name of the new preset.
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String container) {
        return createPreset(presetName, null, container, true, null, null, null, null, null);
    }
        
    /**
     * Create a preset which only convert source media file to a different container format without changing the file
     * contents.
     * 
     * @param presetName The name of the new preset.
     * @param description The description of the new preset
     * @param container The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * 
     */
    public CreatePresetResponse createPreset(String presetName, String description, String container) {
        return createPreset(presetName, description, container, true, null, null, null, null, null);
    }
        
    /**
     * Create a preset which help to convert media files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param transmux    If true, means only convert source media file to a different container format without changing
     *            the file contents. 
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, boolean transmux, Clip clip, Audio audio,
            Video video, Encryption encryption) {
        return createPreset(presetName, description, container, false, clip, audio, video, encryption, null);
    }
    
    /**
     * Create a preset which help to convert media files on be played in a wide range of devices.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param transmux    If true, means only convert source media file to a different container format without changing
     *            the file contents. 
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarkId Specify the watermarkId.
     * 
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, boolean transmux, Clip clip, Audio audio,
            Video video, Encryption encryption, String watermarkId) {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName(presetName);
        request.setDescription(description);
        request.setContainer(container);
        request.setTransmux(transmux);
        request.setClip(clip);
        request.setAudio(audio);
        request.setVideo(video);
        request.setEncryption(encryption);
        request.setWatermarkId(watermarkId);
        
        return createPreset(request);
    }

    /**
     * Create a preset which help to convert media files on be played in a wide range of devices. This version
     * contains all parameters for creating a preset except watermarkId, since watermarks and watermarkId is conflict.
     *
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param container   The container type for the output file. Valid values include mp4, flv, hls, mp3, m4a.
     * @param transmux    If true, means only convert source media file to a different container format without changing
     *            the file contents.
     * @param clip        The clip property of the preset.
     * @param audio       Specify the audio format of target file.
     * @param video       Specify the video format of target file.
     * @param encryption  Specify the encryption property of target file.
     * @param watermarks  Specify the watermarks.
     * @param transCfg    Specify the transcoding configuration.
     * @param extraCfg    Specify the extra configuration.
     *
     */
    public CreatePresetResponse createPreset(
            String presetName, String description, String container, boolean transmux, Clip clip, Audio audio,
            Video video, Encryption encryption, Watermarks watermarks, TransCfg transCfg, ExtraCfg extraCfg) {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName(presetName);
        request.setDescription(description);
        request.setContainer(container);
        request.setTransmux(transmux);
        request.setClip(clip);
        request.setAudio(audio);
        request.setVideo(video);
        request.setEncryption(encryption);
        request.setWatermarks(watermarks);
        request.setTransCfg(transCfg);
        request.setExtraCfg(extraCfg);

        return createPreset(request);
    }

    /**
     * Create a preset which help to convert media files on be played in a wide range of devices.
     * 
     * @param request The request object containing all options for deleting presets.
     */
    public CreatePresetResponse createPreset(CreatePresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, PRESET);

        return invokeHttpClient(internalRequest, CreatePresetResponse.class);
    }

    /**
     * Update a preset.
     *
     * @param request The request object containing all options for updating presets.
     */
    public UpdatePresetResponse updatePreset(UpdatePresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, PRESET, request.getPresetName());
        return invokeHttpClient(internalRequest, UpdatePresetResponse.class);
    }

    /**
     * List all system and user's preset.
     *
     * @return The list of all available preset. 
     */
    public ListPresetsResponse listPresets() {
        ListPresetsRequest request = new ListPresetsRequest();
        return listPresets(request);
    }

    /**
     * List all system and user's preset.
     * 
     * @param request The request object containing all options for listing presets.
     *
     * @return The list of all available preset. 
     */
    public ListPresetsResponse listPresets(ListPresetsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PRESET);
        return invokeHttpClient(internalRequest, ListPresetsResponse.class);
    }

    /**
     * Gets a preset with specified name.
     *
     * @param presetName The name of a preset.
     * 
     * @return The information of the preset. 
     */
    public GetPresetResponse getPreset(String presetName) {
        GetPresetRequest request = new GetPresetRequest();
        request.setPresetName(presetName);
        return getPreset(request);
    }

    /**
     * Gets a preset with specified name.
     *
     * @param request The request object containing all options for getting a preset.
     * 
     * @return The information of the preset. 
     */
    public GetPresetResponse getPreset(GetPresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPresetName(),
                checkEmptyExceptionMessageFormat(PRESETNAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, PRESET, request.getPresetName());

        return invokeHttpClient(internalRequest, GetPresetResponse.class);
    }

    /**
     * Deletes a preset with specified name.
     *
     * @param presetName The name of a preset.
     * 
     */
    public void deletePreset(String presetName) {
        DeletePresetRequest request = new DeletePresetRequest();
        request.setPresetName(presetName);
        deletePreset(request);
    }

    /**
     * Deletes a preset with specified name.
     *
     * @param request The request object containing all options for deleting a preset.
     * 
     */
    public void deletePreset(DeletePresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPresetName(),
                checkEmptyExceptionMessageFormat(PRESETNAME_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, PRESET, request.getPresetName());

        invokeHttpClient(internalRequest, DeletePresetResponse.class);
    }

    /**
     * Retrieve the media information of an object in Bos bucket.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     *     
     * @return The media information of an object in Bos bucket.
     */
    public GetMediaInfoOfFileResponse getMediaInfoOfFile(String bucket, String key) {
        GetMediaInfoOfFileRequest request = new GetMediaInfoOfFileRequest();
        request.setBucket(bucket);
        request.setKey(key);
        return getMediaInfoOfFile(request);
    }

    /**
     * Retrieve the media information of an object in Bos bucket.
     *
     * @param request The request object containing all options for retrieving media information.
     *     
     * @return The media information of an object in Bos bucket.
     */
    public GetMediaInfoOfFileResponse getMediaInfoOfFile(GetMediaInfoOfFileRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBucket(),
                checkEmptyExceptionMessageFormat(BUCKET_MESSAGE_KEY));
        checkStringNotEmpty(request.getKey(), checkEmptyExceptionMessageFormat(KEY_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, MEDIAINFO);
        internalRequest.addParameter(BUCKET_MESSAGE_KEY, request.getBucket());
        internalRequest.addParameter(KEY_MESSAGE_KEY, request.getKey());
        return invokeHttpClient(internalRequest, GetMediaInfoOfFileResponse.class);
    }
    
    /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalOffsetInPixel  The horizontal offset in pixels.
     * @param verticalOffsetInPixel    The vertical offset in pixels.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    @Deprecated
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, int horizontalOffsetInPixel, int verticalOffsetInPixel) {
        
        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalOffsetInPixel(horizontalOffsetInPixel)
                        .withVerticalOffsetInPixel(verticalOffsetInPixel);
        return createWaterMark(request);
    }
    
    /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment);

        return createWaterMark(request);
    }
    
    /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * @param horizontalOffsetInPixel  The horizontal offset in pixels.
     * @param verticalOffsetInPixel    The vertical offset in pixels.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment,
            int horizontalOffsetInPixel, int verticalOffsetInPixel) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment)
                        .withHorizontalOffsetInPixel(horizontalOffsetInPixel)
                        .withVerticalOffsetInPixel(verticalOffsetInPixel);

        return createWaterMark(request);
    }

    /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * @param dx  The horizontal offset.
     * @param dy  The vertical offset.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment,
            String dx, String dy) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment)
                        .withDx(dx)
                        .withDy(dy);

        return createWaterMark(request);
    }

        /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * @param dx  The horizontal offset.
     * @param dy  The vertical offset.
     * @param width  The width of watermark.
     * @param height  The height of watermark.
     * 
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment,
            String dx, String dy, String width, String height) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment)
                        .withDx(dx)
                        .withDy(dy)
                        .withWidth(width)
                        .withHeight(height);

        return createWaterMark(request);
    }

    /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * @param horizontalOffsetInPixel  The horizontal offset in pixels.
     * @param verticalOffsetInPixel    The vertical offset in pixels.
     * @param timeline    The vertical offset in pixels.
     * @param repeated    The vertical offset in pixels.
     * @param allowScaling    The vertical offset in pixels.
     *
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment,
            int horizontalOffsetInPixel, int verticalOffsetInPixel,
            Timeline timeline, Integer repeated, Boolean allowScaling) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment)
                        .withHorizontalOffsetInPixel(horizontalOffsetInPixel)
                        .withVerticalOffsetInPixel(verticalOffsetInPixel)
                        .withTimeline(timeline)
                        .withRepeated(repeated)
                        .withAllowScaling(allowScaling);

        return createWaterMark(request);
    }

    /**
     * Creates a watermark and return water mark ID.
     *
     * @param bucket  The bucket name of Bos object which you want to read.
     * @param key     The key name of Bos object which your want to read.
     * @param horizontalAlignment  The horizontal alignment, includes left, center, right.
     * @param verticalAlignment    The vertical alignment, includes top, center, bottom.
     * @param dx  The horizontal offset.
     * @param dy  The vertical offset.
     * @param width  The width of watermark.
     * @param height  The height of watermark.
     * @param timeline    The vertical offset in pixels.
     * @param repeated    The vertical offset in pixels.
     *
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(
            String bucket, String key, String horizontalAlignment, String verticalAlignment,
            String dx, String dy, String width, String height,
            Timeline timeline, Integer repeated) {

        CreateWaterMarkRequest request =
                new CreateWaterMarkRequest().withBucket(bucket).withKey(key)
                        .withHorizontalAlignment(horizontalAlignment)
                        .withVerticalAlignment(verticalAlignment)
                        .withDx(dx)
                        .withDy(dy)
                        .withWidth(width)
                        .withHeight(height)
                        .withTimeline(timeline)
                        .withRepeated(repeated);

        return createWaterMark(request);
    }


    /**
     * Creates a water mark and return water mark ID
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return watermarkId the unique ID of the new water mark.
     */
    public CreateWaterMarkResponse createWaterMark(CreateWaterMarkRequest request) {
        checkStringNotEmpty(request.getBucket(),
                checkEmptyExceptionMessageFormat(BUCKET_MESSAGE_KEY));
        checkStringNotEmpty(request.getKey(), checkEmptyExceptionMessageFormat(KEY_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, WATER_MARK);
        
        return invokeHttpClient(internalRequest, CreateWaterMarkResponse.class);
    }

    /**
     * Get a water mark for a given water mark ID. 
     *
     * @param watermarkId The ID of water mark.
     * @return The information of the water mark.
     * 
     */
    public GetWaterMarkResponse getWaterMark(String watermarkId) {
        GetWaterMarkRequest request = new GetWaterMarkRequest().withWatermarkId(watermarkId);
        return getWaterMark(request);
    }

    /**
     * Get a water mark for a given water mark ID. 
     *
     * @param request The request object containing all options for getting water mark.
     * @return The information of the water mark.
     */
    public GetWaterMarkResponse getWaterMark(GetWaterMarkRequest request) {
        checkStringNotEmpty(request.getWatermarkId(), checkEmptyExceptionMessageFormat(WATERMARKID_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, WATER_MARK, request.getWatermarkId());
        
        return invokeHttpClient(internalRequest, GetWaterMarkResponse.class);
    }
    
    /**
     * List all water mark.
     *
     * @return The list of all user's water mark.
     * 
     */
    public ListWaterMarkResponse listWaterMark() {
        ListWaterMarkRequest request = new ListWaterMarkRequest();
        return listWaterMark(request);
    }
    
    /**
     * List all water mark.
     *
     * @return The list of all user's water mark.
     * 
     */
    public ListWaterMarkResponse listWaterMark(ListWaterMarkRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, WATER_MARK);
        
        return invokeHttpClient(internalRequest, ListWaterMarkResponse.class);
    }

    /**
     * Delete a water mark. 
     *
     * @param watermarkId The ID of water mark.
     * 
     */
    public void deleteWaterMark(String watermarkId) {
        DeleteWaterMarkRequest request = new DeleteWaterMarkRequest().withWatermarkId(watermarkId);
        deleteWaterMark(request);
    }

    /**
     * Delete a water mark. 
     *
     * @param request The request object containing all options for deleting water mark.
     * 
     */
    public void deleteWaterMark(DeleteWaterMarkRequest request) {
        checkStringNotEmpty(request.getWatermarkId(), checkEmptyExceptionMessageFormat(WATERMARKID_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, WATER_MARK, request.getWatermarkId());
        
        invokeHttpClient(internalRequest, DeleteWaterMarkResponse.class);
    }
    
    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param target The property container of target object.
     * @param capture The property container of thumbnail generating policies.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(
            String pipelineName, String sourceKey, ThumbnailTarget target, ThumbnailCapture capture) {
        
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target)
                        .withCapture(capture);
        
        return createThumbnailJob(request);
    }

    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param presetName The name of a thumbnail preset.
     * @param sourceKey The key of source object.
     * @param targetKeyPrefix The property container of target object.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(String pipelineName, String presetName, String sourceKey, 
            String targetKeyPrefix) {
        
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);

        ThumbnailTarget target = new ThumbnailTarget();
        target.setKeyPrefix(targetKeyPrefix);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withPresetName(presetName)
                        .withSource(source).withTarget(target);

        return createThumbnailJob(request);
    }

    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param presetName The name of a thumbnail preset.
     * @param sourceKey The key of source object.
     * @param targetKeyPrefix The property container of target object.
     * @param delogoArea The property container of delogo Area.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(String pipelineName, String presetName, String sourceKey, 
            String targetKeyPrefix, Area delogoArea) {
        
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);

        ThumbnailTarget target = new ThumbnailTarget();
        target.setKeyPrefix(targetKeyPrefix);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withPresetName(presetName)
                        .withSource(source).withTarget(target).withDelogoArea(delogoArea);

        return createThumbnailJob(request);
    }

    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param presetName The name of a thumbnail preset.
     * @param sourceKey The key of source object.
     * @param targetKeyPrefix The property container of target object.
     * @param delogoArea The property container of delogo Area.
     * @param crop The property container of crop Area.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(String pipelineName, String presetName, String sourceKey, 
            String targetKeyPrefix, Area delogoArea, Area crop) {
        
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);

        ThumbnailTarget target = new ThumbnailTarget();
        target.setKeyPrefix(targetKeyPrefix);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withPresetName(presetName)
                        .withSource(source).withTarget(target).withDelogoArea(delogoArea).withCrop(crop);
        
        return createThumbnailJob(request);
    }

    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param target The property container of target object.
     * @param capture The property container of thumbnail generating policies.
     * @param delogoArea The property container of delogo Area.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(
            String pipelineName, String sourceKey, ThumbnailTarget target,
            ThumbnailCapture capture, Area delogoArea) {

        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);

        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target)
                        .withCapture(capture).withDelogoArea(delogoArea);

        return createThumbnailJob(request);
    }

    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param target The property container of target object.
     * @param capture The property container of thumbnail generating policies.
     * @param delogoArea The property container of delogo Area.
     * @param crop The property container of crop Area.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(
            String pipelineName, String sourceKey, ThumbnailTarget target,
            ThumbnailCapture capture, Area delogoArea, Area crop) {

        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);

        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target)
                        .withCapture(capture).withDelogoArea(delogoArea).withCrop(crop);

        return createThumbnailJob(request);
    }
    
    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(String pipelineName, String sourceKey) {
        ThumbnailSource source = new ThumbnailSource();
        source.setKey(sourceKey);
        
        CreateThumbnailJobRequest request =
                new CreateThumbnailJobRequest().withPipelineName(pipelineName).withSource(source);
        
        return createThumbnailJob(request);
    }
    
    /**
     * Creates a thumbnail job and return job ID.
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return the unique ID of the new thumbnail job.
     */
    public CreateThumbnailJobResponse createThumbnailJob(CreateThumbnailJobRequest request) {
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        checkNotNull(request.getSource(),  checkEmptyExceptionMessageFormat(SOURCE_MESSAGE_KEY));
        checkStringNotEmpty(request.getSource().getKey(),
                checkEmptyExceptionMessageFormat(SOURCEKEY_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, THUMBNAIL);
        
        return invokeHttpClient(internalRequest, CreateThumbnailJobResponse.class);
    }
    
    /**
     * Get information of thumbnail job.
     *
     * @param jobId The unique ID of thumbnail job.
     *
     * @return The information of the thumbnail job.
     */
    public GetThumbnailJobResponse getThumbnailJob(String jobId) {
        GetThumbnailJobRequest request = new GetThumbnailJobRequest().withJobId(jobId);
        
        return getThumbnailJob(request);
    }
    
    /**
     * Get information of thumbnail job.
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return The information of the thumbnail job.
     */
    public GetThumbnailJobResponse getThumbnailJob(GetThumbnailJobRequest request) {
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, THUMBNAIL, request.getJobId());
        
        return invokeHttpClient(internalRequest, GetThumbnailJobResponse.class);
    }
    
    /**
     * List thumbnail jobs for a given pipeline.
     *
     * @param pipelineName The name of a pipeline.
     *
     * @return List of thumbnail jobs.
     */
    public ListThumbnailJobsResponse listThumbnailJobs(String pipelineName) {
        ListThumbnailJobsRequest request = new ListThumbnailJobsRequest().withPipeline(pipelineName);
        
        return listThumbnailJobs(request);
    }
    
    /**
     * List thumbnail jobs for a given pipeline.
     *
     * @param request The request object containing all options for creating new water mark.
     *
     * @return List of thumbnail jobs.
     */
    public ListThumbnailJobsResponse listThumbnailJobs(ListThumbnailJobsRequest request) {
        checkStringNotEmpty(request.getPipeline(), checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, THUMBNAIL);
        internalRequest.addParameter(PIPELINENAME_MESSAGE_KEY, request.getPipeline());
        return invokeHttpClient(internalRequest, ListThumbnailJobsResponse.class);
    }

    /**
     * get a notification.
     *
     * @param name notification name
     */
    public GetNotificationResponse getNotification(String name) {
        checkNotNull(name, checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        GetNotificationRequest request = new GetNotificationRequest()
                .withName(name);
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request
                , NOTIFICATION, request.getName());

        return invokeHttpClient(internalRequest, GetNotificationResponse.class);
    }

    /**
     * list all notifications.
     *
     */
    public ListNotificationsResponse listNotification() {
        ListNotificationsRequest request = new ListNotificationsRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, NOTIFICATION);
        return invokeHttpClient(internalRequest, ListNotificationsResponse.class);
    }

    /**
     * Create a notification.
     *
     * @param name notification name
     * @param endpoint notification endpoint
     */
    public CreateNotificationResponse createNotification(String name, String endpoint) {
        checkNotNull(name, checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        checkNotNull(endpoint, checkEmptyExceptionMessageFormat(ENDPOINT_MESSAGE_KEY));

        CreateNotificationRequest request = new CreateNotificationRequest()
                .withName(name)
                .withEndpoint(endpoint);
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, NOTIFICATION);

        return invokeHttpClient(internalRequest, CreateNotificationResponse.class);
    }

    /**
     * Delete a notification.
     *
     * @param name notification name
     */
    public DeleteNotificationResponse deleteNotification(String name) {
        checkNotNull(name, checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        DeleteNotificationRequest request = new DeleteNotificationRequest()
                .withName(name);
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, request
                , NOTIFICATION, request.getName());

        return invokeHttpClient(internalRequest, DeleteNotificationResponse.class);
    }

    
    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path, 
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.  
     * </p>
     * @param httpMethod
     *            The HTTP method to use when sending the request.
     * @param request
     *            The original request, as created by the user.
     * @param pathVariables
     *            The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     *         ready for callers to populate any additional headers or
     *         parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String...pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);

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

        if (httpMethod == HttpMethodName.POST
                || httpMethod == HttpMethodName.PUT) {
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

    /**
     * Create a thumbnail preset which help to convert video files to be pictures.
     * 
     * @param presetName  The name of the new preset.
     * @param description The description of the new preset
     * @param target      The output config of the preset.
     * @param capture     The capture mode of the preset;
     */
    public CreateThumbnailPresetResponse createThumbnailPreset(String presetName, String description, 
            ThumbnailPresetTarget target, ThumbnailPresetCapture capture) {
        return createThumbnailPreset(new CreateThumbnailPresetRequest()
                .withDescription(description)
                .withPresetName(presetName)
                .withTarget(target)
                .withCapture(capture));
    }

    /**
     * Create a thumbnail preset which help to convert video files to be pictures.
     * 
     * @param request The request object containing all options for presets.
     */
    public CreateThumbnailPresetResponse createThumbnailPreset(CreateThumbnailPresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, THUMBNAIL_PRESET);

        return invokeHttpClient(internalRequest, CreateThumbnailPresetResponse.class);
    }

    /**
     * Delete a thumbnail preset with specified name.
     *
     * @param presetName The name of a preset.
     * 
     */
    public void deleteThumbnailPreset(String presetName) {
        DeleteThumbnailPresetRequest request = new DeleteThumbnailPresetRequest();
        request.setPresetName(presetName);
        deleteThumbnailPreset(request);
    }

    /**
     * Delete a thumbnail preset with specified name.
     *
     * @param request The request object containing all options for deleting a preset.
     * 
     */
    public void deleteThumbnailPreset(DeleteThumbnailPresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPresetName(),
                checkEmptyExceptionMessageFormat(PRESETNAME_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.DELETE, request, THUMBNAIL_PRESET, request.getPresetName());

        invokeHttpClient(internalRequest, DeletePresetResponse.class);

    }

    /**
     * Get a thumbnail preset with specified name.
     *
     * @param presetName The name of a preset.
     * 
     * @return The information of the preset. 
     */
    public GetThumbnailPresetResponse getThumbnailPreset(String presetName) {
        GetThumbnailPresetRequest request = new GetThumbnailPresetRequest();
        request.setPresetName(presetName);
        return getThumbnailPreset(request);
    }

    /**
     * Get a thumbnail preset with specified name.
     *
     * @param request The request object containing all options for getting a thumbnail preset.
     * 
     * @return The information of the thumbnail preset. 
     */
    public GetThumbnailPresetResponse getThumbnailPreset(GetThumbnailPresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPresetName(),
                checkEmptyExceptionMessageFormat(PRESETNAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, THUMBNAIL_PRESET, 
                request.getPresetName());

        return invokeHttpClient(internalRequest, GetThumbnailPresetResponse.class);
    }

    /**
     * Update a thumbnail preset.
     *
     * @param request The request object containing all options for updating presets.
     */
    public UpdateThumbnailPresetResponse updateThumbnailPreset(UpdateThumbnailPresetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, THUMBNAIL_PRESET, request.getPresetName());
        return invokeHttpClient(internalRequest, UpdateThumbnailPresetResponse.class);
    }

    /**
     * List all user's thumbnail preset.
     *
     * @return The list of all available thumbnail preset. 
     */
    public ListThumbnailPresetsResponse listThumbnailPresets() {
        ListThumbnailPresetsRequest request = new ListThumbnailPresetsRequest();
        return listThumbnailPresets(request);
    }

    /**
     * List all user's thumbnail preset.
     * 
     * @param request The request object containing all options for listing presets.
     *
     * @return The list of all available thumbnail preset. 
     */
    public ListThumbnailPresetsResponse listThumbnailPresets(ListThumbnailPresetsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, THUMBNAIL_PRESET);
        return invokeHttpClient(internalRequest, ListThumbnailPresetsResponse.class);
    }

    /**
     * Creates a subtitle job and return job ID.
     *
     * @param request The request object containing all options for creating new subtitle job.
     *
     * @return the unique ID of the new subtitle job.
     */
    public CreateSubtitleJobResponse createSubtitleJob(CreateSubtitleJobRequest request) {
        checkStringNotEmpty(request.getPipelineName(),
                checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        checkNotNull(request.getSource(),  checkEmptyExceptionMessageFormat(SOURCE_MESSAGE_KEY));
        checkStringNotEmpty(request.getSource().getKey(),
                checkEmptyExceptionMessageFormat(SOURCEKEY_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.POST, request, SUBTITLE);
        
        return invokeHttpClient(internalRequest, CreateSubtitleJobResponse.class);
    }

    /**
     * Creates a subtitle job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param targetKeyPrefix The key prefix of target subtitle file in bos.
     *
     * @return the unique ID of the new subtitle job.
     */
    public CreateSubtitleJobResponse createSubtitleJob(
            String pipelineName, String sourceKey, String targetKeyPrefix) {
        
        SubtitleSource source = new SubtitleSource();
        source.setKey(sourceKey);

        SubtitleTarget target = new SubtitleTarget();
        target.setKeyPrefix(targetKeyPrefix);
        
        CreateSubtitleJobRequest request =
                new CreateSubtitleJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target);
        
        return createSubtitleJob(request);
    }

    /**
     * Creates a subtitle job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param targetKeyPrefix The key prefix of target subtitle file in bos.
     * @param format The format of subtitle file, can be json / srt.
     *
     * @return the unique ID of the new subtitle job.
     */
    public CreateSubtitleJobResponse createSubtitleJob(
            String pipelineName, String sourceKey, String targetKeyPrefix, String format) {
        
        SubtitleSource source = new SubtitleSource();
        source.setKey(sourceKey);

        SubtitleTarget target = new SubtitleTarget();
        target.setKeyPrefix(targetKeyPrefix);
        target.addFormat(format);
        
        CreateSubtitleJobRequest request =
                new CreateSubtitleJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target);
        
        return createSubtitleJob(request);
    }

    /**
     * Creates a subtitle job and return job ID.
     *
     * @param pipelineName The name of a pipeline.
     * @param sourceKey The key of source object.
     * @param targetKeyPrefix The key prefix of target subtitle file in bos.
     * @param formats The format of subtitle file, can be json / srt.
     *
     * @return the unique ID of the new subtitle job.
     */
    public CreateSubtitleJobResponse createSubtitleJob(
            String pipelineName, String sourceKey, String targetKeyPrefix, List<String> formats) {
        
        SubtitleSource source = new SubtitleSource();
        source.setKey(sourceKey);

        SubtitleTarget target = new SubtitleTarget();
        target.setKeyPrefix(targetKeyPrefix);
        target.setFormats(formats);
        
        CreateSubtitleJobRequest request =
                new CreateSubtitleJobRequest().withPipelineName(pipelineName).withSource(source).withTarget(target);
        
        return createSubtitleJob(request);
    }


    /**
     * Get information of subtitle job.
     *
     * @param jobId The unique ID of subtitle job.
     *
     * @return The information of the subtitle job.
     */
    public GetSubtitleJobResponse getSubtitleJob(String jobId) {
        GetSubtitleJobRequest request = new GetSubtitleJobRequest().withJobId(jobId);
        
        return getSubtitleJob(request);
    }
    
    /**
     * Get information of subtitle job.
     *
     * @param request The request object containing all options for getting a subtitle job.
     *
     * @return The information of the subtitle job.
     */
    public GetSubtitleJobResponse getSubtitleJob(GetSubtitleJobRequest request) {
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest =
                createRequest(HttpMethodName.GET, request, SUBTITLE, request.getJobId());
        
        return invokeHttpClient(internalRequest, GetSubtitleJobResponse.class);
    }


    /**
     * List subtitle jobs for a given pipeline.
     *
     * @param pipelineName The name of a pipeline.
     *
     * @return List of subtitle jobs.
     */
    public ListSubtitleJobsResponse listSubtitleJobs(String pipelineName) {
        ListSubtitleJobsRequest request = new ListSubtitleJobsRequest().withPipeline(pipelineName);
        
        return listSubtitleJobs(request);
    }
    
    /**
     * List subtitle jobs for a given pipeline.
     *
     * @param request The request object containing all options for getting subtitle jobs.
     *
     * @return List of subtitle jobs.
     */
    public ListSubtitleJobsResponse listSubtitleJobs(ListSubtitleJobsRequest request) {
        checkStringNotEmpty(request.getPipeline(), checkEmptyExceptionMessageFormat(PIPELINENAME_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, SUBTITLE);
        internalRequest.addParameter(PIPELINENAME_MESSAGE_KEY, request.getPipeline());
        return invokeHttpClient(internalRequest, ListSubtitleJobsResponse.class);
    }

    /**
     * Get transcoding job encryption key
     *
     * @param request The request object containing all options for getting encryption key.
     *
     * @return Response Object contains transcoding encryption Aes key.
     */
    public GetTranscodingEncryptionKeyResponse getTranscodingEncryptionKey(GetTranscodingEncryptionKeyRequest request) {
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, 
                request, TRANSCODING_KEY, request.getJobId());
                
        return invokeHttpClient(internalRequest, GetTranscodingEncryptionKeyResponse.class);
    }

    /**
     * Get transcoding job encryption key
     *
     * @param jobId The job ID want to query transcoding encryption key.
     *
     * @return Response Object contains transcoding encryption Aes keyl.
     */
    public GetTranscodingEncryptionKeyResponse getTranscodingEncryptionKey(String jobId) {
        return getTranscodingEncryptionKey(new GetTranscodingEncryptionKeyRequest().withJobId(jobId));
    }

}
