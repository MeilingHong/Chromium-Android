// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service.proto

package org.chromium.chrome.browser.autofill_assistant.proto;

/**
 * <pre>
 * A reference to an unique element on the page, possibly nested in frames.
 * </pre>
 *
 * Protobuf type {@code autofill_assistant.ElementReferenceProto}
 */
public  final class ElementReferenceProto extends
    com.google.protobuf.GeneratedMessageLite<
        ElementReferenceProto, ElementReferenceProto.Builder> implements
    // @@protoc_insertion_point(message_implements:autofill_assistant.ElementReferenceProto)
    ElementReferenceProtoOrBuilder {
  private ElementReferenceProto() {
    selectors_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
  }
  private int bitField0_;
  public static final int SELECTORS_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.ProtobufList<String> selectors_;
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  public java.util.List<String> getSelectorsList() {
    return selectors_;
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  public int getSelectorsCount() {
    return selectors_.size();
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  public java.lang.String getSelectors(int index) {
    return selectors_.get(index);
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  public com.google.protobuf.ByteString
      getSelectorsBytes(int index) {
    return com.google.protobuf.ByteString.copyFromUtf8(
        selectors_.get(index));
  }
  private void ensureSelectorsIsMutable() {
    if (!selectors_.isModifiable()) {
      selectors_ =
          com.google.protobuf.GeneratedMessageLite.mutableCopy(selectors_);
     }
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  private void setSelectors(
      int index, java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  ensureSelectorsIsMutable();
    selectors_.set(index, value);
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  private void addSelectors(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  ensureSelectorsIsMutable();
    selectors_.add(value);
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  private void addAllSelectors(
      java.lang.Iterable<java.lang.String> values) {
    ensureSelectorsIsMutable();
    com.google.protobuf.AbstractMessageLite.addAll(
        values, selectors_);
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  private void clearSelectors() {
    selectors_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
  }
  /**
   * <pre>
   * A sequence of CSS selectors. Any non-final CSS selector is expected to
   * arrive at a frame or an iframe, i.e. an element that contains another
   * document.
   * APIs are free to reject element references that do not refer to unique
   * elements (i.e. resolve to more than one element on the page).
   * </pre>
   *
   * <code>repeated string selectors = 2;</code>
   */
  private void addSelectorsBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  ensureSelectorsIsMutable();
    selectors_.add(value.toStringUtf8());
  }

  public static final int PSEUDO_TYPE_FIELD_NUMBER = 3;
  private int pseudoType_;
  /**
   * <pre>
   * An optional pseudo type. This pseudo type is associated to the final
   * element matched by |selector|, which means that we currently don't handle
   * matching an element inside a pseudo element.
   * </pre>
   *
   * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
   */
  public boolean hasPseudoType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * An optional pseudo type. This pseudo type is associated to the final
   * element matched by |selector|, which means that we currently don't handle
   * matching an element inside a pseudo element.
   * </pre>
   *
   * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
   */
  public org.chromium.chrome.browser.autofill_assistant.proto.PseudoType getPseudoType() {
    org.chromium.chrome.browser.autofill_assistant.proto.PseudoType result = org.chromium.chrome.browser.autofill_assistant.proto.PseudoType.forNumber(pseudoType_);
    return result == null ? org.chromium.chrome.browser.autofill_assistant.proto.PseudoType.UNDEFINED : result;
  }
  /**
   * <pre>
   * An optional pseudo type. This pseudo type is associated to the final
   * element matched by |selector|, which means that we currently don't handle
   * matching an element inside a pseudo element.
   * </pre>
   *
   * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
   */
  private void setPseudoType(org.chromium.chrome.browser.autofill_assistant.proto.PseudoType value) {
    if (value == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x00000001;
    pseudoType_ = value.getNumber();
  }
  /**
   * <pre>
   * An optional pseudo type. This pseudo type is associated to the final
   * element matched by |selector|, which means that we currently don't handle
   * matching an element inside a pseudo element.
   * </pre>
   *
   * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
   */
  private void clearPseudoType() {
    bitField0_ = (bitField0_ & ~0x00000001);
    pseudoType_ = 0;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < selectors_.size(); i++) {
      output.writeString(2, selectors_.get(i));
    }
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeEnum(3, pseudoType_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < selectors_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeStringSizeNoTag(selectors_.get(i));
      }
      size += dataSize;
      size += 1 * getSelectorsList().size();
    }
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, pseudoType_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * <pre>
   * A reference to an unique element on the page, possibly nested in frames.
   * </pre>
   *
   * Protobuf type {@code autofill_assistant.ElementReferenceProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto, Builder> implements
      // @@protoc_insertion_point(builder_implements:autofill_assistant.ElementReferenceProto)
      org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProtoOrBuilder {
    // Construct using org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public java.util.List<String>
        getSelectorsList() {
      return java.util.Collections.unmodifiableList(
          instance.getSelectorsList());
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public int getSelectorsCount() {
      return instance.getSelectorsCount();
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public java.lang.String getSelectors(int index) {
      return instance.getSelectors(index);
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSelectorsBytes(int index) {
      return instance.getSelectorsBytes(index);
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public Builder setSelectors(
        int index, java.lang.String value) {
      copyOnWrite();
      instance.setSelectors(index, value);
      return this;
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public Builder addSelectors(
        java.lang.String value) {
      copyOnWrite();
      instance.addSelectors(value);
      return this;
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public Builder addAllSelectors(
        java.lang.Iterable<java.lang.String> values) {
      copyOnWrite();
      instance.addAllSelectors(values);
      return this;
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public Builder clearSelectors() {
      copyOnWrite();
      instance.clearSelectors();
      return this;
    }
    /**
     * <pre>
     * A sequence of CSS selectors. Any non-final CSS selector is expected to
     * arrive at a frame or an iframe, i.e. an element that contains another
     * document.
     * APIs are free to reject element references that do not refer to unique
     * elements (i.e. resolve to more than one element on the page).
     * </pre>
     *
     * <code>repeated string selectors = 2;</code>
     */
    public Builder addSelectorsBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.addSelectorsBytes(value);
      return this;
    }

    /**
     * <pre>
     * An optional pseudo type. This pseudo type is associated to the final
     * element matched by |selector|, which means that we currently don't handle
     * matching an element inside a pseudo element.
     * </pre>
     *
     * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
     */
    public boolean hasPseudoType() {
      return instance.hasPseudoType();
    }
    /**
     * <pre>
     * An optional pseudo type. This pseudo type is associated to the final
     * element matched by |selector|, which means that we currently don't handle
     * matching an element inside a pseudo element.
     * </pre>
     *
     * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
     */
    public org.chromium.chrome.browser.autofill_assistant.proto.PseudoType getPseudoType() {
      return instance.getPseudoType();
    }
    /**
     * <pre>
     * An optional pseudo type. This pseudo type is associated to the final
     * element matched by |selector|, which means that we currently don't handle
     * matching an element inside a pseudo element.
     * </pre>
     *
     * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
     */
    public Builder setPseudoType(org.chromium.chrome.browser.autofill_assistant.proto.PseudoType value) {
      copyOnWrite();
      instance.setPseudoType(value);
      return this;
    }
    /**
     * <pre>
     * An optional pseudo type. This pseudo type is associated to the final
     * element matched by |selector|, which means that we currently don't handle
     * matching an element inside a pseudo element.
     * </pre>
     *
     * <code>optional .autofill_assistant.PseudoType pseudo_type = 3;</code>
     */
    public Builder clearPseudoType() {
      copyOnWrite();
      instance.clearPseudoType();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:autofill_assistant.ElementReferenceProto)
  }
  protected final Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto();
      }
      case IS_INITIALIZED: {
        return DEFAULT_INSTANCE;
      }
      case MAKE_IMMUTABLE: {
        selectors_.makeImmutable();
        return null;
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case VISIT: {
        Visitor visitor = (Visitor) arg0;
        org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto other = (org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto) arg1;
        selectors_= visitor.visitList(selectors_, other.selectors_);
        pseudoType_ = visitor.visitInt(hasPseudoType(), pseudoType_,
            other.hasPseudoType(), other.pseudoType_);
        if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
            .INSTANCE) {
          bitField0_ |= other.bitField0_;
        }
        return this;
      }
      case MERGE_FROM_STREAM: {
        com.google.protobuf.CodedInputStream input =
            (com.google.protobuf.CodedInputStream) arg0;
        com.google.protobuf.ExtensionRegistryLite extensionRegistry =
            (com.google.protobuf.ExtensionRegistryLite) arg1;
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!parseUnknownField(tag, input)) {
                  done = true;
                }
                break;
              }
              case 18: {
                String s = input.readString();
                if (!selectors_.isModifiable()) {
                  selectors_ =
                      com.google.protobuf.GeneratedMessageLite.mutableCopy(selectors_);
                }
                selectors_.add(s);
                break;
              }
              case 24: {
                int rawValue = input.readEnum();
                org.chromium.chrome.browser.autofill_assistant.proto.PseudoType value = org.chromium.chrome.browser.autofill_assistant.proto.PseudoType.forNumber(rawValue);
                if (value == null) {
                  super.mergeVarintField(3, rawValue);
                } else {
                  bitField0_ |= 0x00000001;
                  pseudoType_ = rawValue;
                }
                break;
              }
            }
          }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw new RuntimeException(e.setUnfinishedMessage(this));
        } catch (java.io.IOException e) {
          throw new RuntimeException(
              new com.google.protobuf.InvalidProtocolBufferException(
                  e.getMessage()).setUnfinishedMessage(this));
        } finally {
        }
      }
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        if (PARSER == null) {    synchronized (org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto.class) {
            if (PARSER == null) {
              PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            }
          }
        }
        return PARSER;
      }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:autofill_assistant.ElementReferenceProto)
  private static final org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ElementReferenceProto();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static org.chromium.chrome.browser.autofill_assistant.proto.ElementReferenceProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<ElementReferenceProto> PARSER;

  public static com.google.protobuf.Parser<ElementReferenceProto> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

