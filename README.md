## API

<docgen-index>

* [`getIP()`](#getip)
* [`getSSID()`](#getssid)
* [`connect(...)`](#connect)
* [`connectPrefix(...)`](#connectprefix)
* [`disconnect()`](#disconnect)
* [`getScanResults(...)`](#getscanresults)
* [`addListener('wifiStatusChange', ...)`](#addlistenerwifistatuschange)
* [`removeAllListeners()`](#removealllisteners)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getIP()

```typescript
getIP() => Promise<{ ip: string | null; }>
```

**Returns:** <code>Promise&lt;{ ip: string | null; }&gt;</code>

--------------------


### getSSID()

```typescript
getSSID() => Promise<{ ssid: string | null; }>
```

**Returns:** <code>Promise&lt;{ ssid: string | null; }&gt;</code>

--------------------


### connect(...)

```typescript
connect(options: { ssid: string; password?: string; joinOnce?: boolean; isHiddenSsid?: boolean; }) => Promise<{ ssid: string | null; }>
```

| Param         | Type                                                                                          |
| ------------- | --------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ ssid: string; password?: string; joinOnce?: boolean; isHiddenSsid?: boolean; }</code> |

**Returns:** <code>Promise&lt;{ ssid: string | null; }&gt;</code>

--------------------


### connectPrefix(...)

```typescript
connectPrefix(options: { ssid: string; password?: string; joinOnce?: boolean; }) => Promise<{ ssid: string | null; }>
```

| Param         | Type                                                                  |
| ------------- | --------------------------------------------------------------------- |
| **`options`** | <code>{ ssid: string; password?: string; joinOnce?: boolean; }</code> |

**Returns:** <code>Promise&lt;{ ssid: string | null; }&gt;</code>

--------------------


### disconnect()

```typescript
disconnect() => Promise<void>
```

--------------------


### getScanResults(...)

```typescript
getScanResults(filter?: string | undefined) => Promise<{ ssids: any[]; }>
```

| Param        | Type                |
| ------------ | ------------------- |
| **`filter`** | <code>string</code> |

**Returns:** <code>Promise&lt;{ ssids: any[]; }&gt;</code>

--------------------


### addListener('wifiStatusChange', ...)

```typescript
addListener(eventName: "wifiStatusChange", listenerFunc: WifiStatusChangeListener) => Promise<PluginListenerHandle> & PluginListenerHandle
```

| Param              | Type                                                                          |
| ------------------ | ----------------------------------------------------------------------------- |
| **`eventName`**    | <code>'wifiStatusChange'</code>                                               |
| **`listenerFunc`** | <code><a href="#wifistatuschangelistener">WifiStatusChangeListener</a></code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### WifiStatus

| Prop            | Type                 |
| --------------- | -------------------- |
| **`connected`** | <code>boolean</code> |


### Type Aliases


#### WifiStatusChangeListener

<code>(status: <a href="#wifistatus">WifiStatus</a>): void</code>

</docgen-api>