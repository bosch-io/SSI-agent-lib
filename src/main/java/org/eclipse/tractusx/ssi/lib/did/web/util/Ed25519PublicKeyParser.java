/*
 * ******************************************************************************
 * Copyright (c) 2021,2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * *******************************************************************************
 */

package org.eclipse.tractusx.ssi.lib.did.web.util;

import java.io.StringReader;
import lombok.SneakyThrows;
import org.bouncycastle.util.io.pem.PemReader;
import org.eclipse.tractusx.ssi.lib.model.MultibaseString;
import org.eclipse.tractusx.ssi.lib.model.base.MultibaseFactory;

/** The type Ed 25519 public key parser. */
public class Ed25519PublicKeyParser {

  /**
   * Parses public key in format -----BEGIN PUBLIC KEY-----
   * MCowBQYDK2VwAyEABqAmUe/amV/nAVUt01XyrLpmQLOyLqF6LnAkH4QdyqI= -----END PUBLIC KEY-----
   *
   * @param publicKey the public key
   * @return public key as multibase string
   */
  public static MultibaseString parsePublicKey(String publicKey) {
    final byte[] publicKey64 = readPublicKey(publicKey);
    return MultibaseFactory.create(publicKey64);
  }

  @SneakyThrows
  private static byte[] readPublicKey(String publicKey) {

    PemReader pemReader = new PemReader(new StringReader(publicKey));
    return pemReader.readPemObject().getContent();
  }
}
