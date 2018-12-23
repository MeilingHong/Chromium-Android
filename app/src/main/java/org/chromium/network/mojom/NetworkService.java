
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/network/public/mojom/network_service.mojom
//

package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;


public interface NetworkService extends org.chromium.mojo.bindings.Interface {



    public interface Proxy extends NetworkService, org.chromium.mojo.bindings.Interface.Proxy {
    }

    Manager<NetworkService, NetworkService.Proxy> MANAGER = NetworkService_Internal.MANAGER;


    void setClient(
NetworkServiceClient client);



    void startNetLog(
org.chromium.mojo_base.mojom.File file, int captureMode, org.chromium.mojo_base.mojom.DictionaryValue constants);



    void setSslKeyLogFile(
org.chromium.mojo_base.mojom.FilePath file);



    void createNetworkContext(
org.chromium.mojo.bindings.InterfaceRequest<NetworkContext> context, NetworkContextParams params);



    void configureStubHostResolver(
boolean stubResolverEnabled, DnsOverHttpsServer[] dnsOverHttpsServers);



    void disableQuic(
);



    void setUpHttpAuth(
HttpAuthStaticParams httpAuthStaticParams);



    void configureHttpAuthPrefs(
HttpAuthDynamicParams httpAuthDynamicParams);



    void setRawHeadersAccess(
int processId, boolean allow);



    void getNetworkChangeManager(
org.chromium.mojo.bindings.InterfaceRequest<NetworkChangeManager> networkChangeManager);



    void getNetworkQualityEstimatorManager(
org.chromium.mojo.bindings.InterfaceRequest<NetworkQualityEstimatorManager> networkQualityEstimatorManager);



    void getTotalNetworkUsages(

GetTotalNetworkUsagesResponse callback);

    interface GetTotalNetworkUsagesResponse extends org.chromium.mojo.bindings.Callbacks.Callback1<NetworkUsage[]> { }



    void updateSignedTreeHead(
SignedTreeHead signedTreeHead);



    void updateCrlSet(
org.chromium.mojo_base.mojom.ReadOnlyBuffer crlSet);



    void addCorbExceptionForPlugin(
int processId);



    void removeCorbExceptionForPlugin(
int processId);



    void onApplicationStateChange(
int state);


}
