/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vm.image;

import lombok.Data;

/**
 * The image for the BEC virtual machine.
 */
@Data
public class VmImage {

    /**
     * The name of the BEC virtual machine image.
     */
    private String imageName;

    /**
     * The id of the pvc.
     */
    private String pvcId;

    /**
     * The type of the BEC virtual machine image process, like local.
     */
    private String imageProcessType;
}