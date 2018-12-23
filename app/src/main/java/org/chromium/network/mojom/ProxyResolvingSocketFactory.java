
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     services/network/public/mojom/proxy_resolving_socket.mojom
//

package org.chromium.network.mojom;

import org.chromium.mojo.bindings.DeserializationException;


public interface ProxyResolvingSocketFactory extends org.chromium.mojo.bindings.Interface {



    public interface Proxy extends ProxyResolvingSocketFactory, org.chromium.mojo.bindings.Interface.Proxy {
    }

    Manager<ProxyResolvingSocketFactory, ProxyResolvingSocketFactory.Proxy> MANAGER = ProxyResolvingSocketFactory_Internal.MANAGER;


    void createProxyResolvingSocket(
org.chromium.url.mojom.Url url, boolean useTls, MutableNetworkTrafficAnnotationTag trafficAnnotation, org.chromium.mojo.bindings.InterfaceRequest<ProxyResolvingSocket> socket, SocketObserver observer, 
CreateProxyResolvingSocketResponse callback);

    interface CreateProxyResolvingSocketResponse extends org.chromium.mojo.bindings.Callbacks.Callback5<Integer, org.chromium.net.interfaces.IpEndPoint, org.chromium.net.interfaces.IpEndPoint, org.chromium.mojo.system.DataPipe.ConsumerHandle, org.chromium.mojo.system.DataPipe.ProducerHandle> { }


}
