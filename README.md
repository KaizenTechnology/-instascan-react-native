![Logo](https://sdk.kaizentech.net/instascan_logo.jpeg)

# InstaScan React Native

InstaScan enables smartphones to scan loyalty pincodes, reduce effort and speed up process.

  
## Features

- Pre-Processing algorithm speed up to process
- Pincode role design for accuracy
- On device, secure scan with InstaScan
- Wide range of support

  

## Install

Install InstaScan 

npm:
```bash
  npm i instascan-react-native
```
yarn:
```bash
  yarn add instascan-react-native
```

## Platform Spesific Steps

### IOS

1. Run `cd ios && pod install && cd ..`

2. Add permissions with usage descriptions to your app Info.plist:
```xml
<key>NSCameraUsageDescription</key>
<string>Your message to user when the camera is accessed for the first time</string>
```

### Android

1. Add permissions to your app `android/app/src/main/AndroidManifest.xml` file:
```xml
<uses-permission android:name="android.permission.CAMERA" />
```

2. Append the following lines to `android/build.gradle`
```gradle
    buildscript {
        ext {
            ...
        }
        repositories {
            ...
            maven{
                url "https://sdk.kaizentech.net/android/"
            }
        }
        dependencies {
        ...
        }
    }
    allprojects {
        repositories {
        ...
            maven{
                url "https://sdk.kaizentech.net/android/"
            }
        }
    }
```
  
# Usage

## Component instance methods
**`resetScan()`** 
After you scan successfully, camera will stop recording and scanning. To restart process, you need to call this method.

**`toggleTorch()`**
You need to call this method in order to open-close torch

**`getTrochStatus(): Promise`**
You can call this method at the beginnig in order to get torch status. This will return true or false


## Props

### `onLoad`
Function to be called when InstaScan is ready and apiKey validated.

### `onPincodeRead`
Function to be called when InstaScan finds pincode.

Event contains the following fields:
- `pinCode` - finded pincode string
- `imagePath` - image for the captured frame 
- `configuration` - configurations for the InstaScan

### `onError`
Function to be called when InstaScan throw error.

Event contains the following fields:
- `code` - error code
- `description` - error message


### `apiKey` Required 
Contact with us in order to get your API key.


### `sampleCount`
It counts how many time pincode needs to confirmed before trigger the onPinCode event.


### `minTextHeight`
Value: float from `0` to `1.0`

Minimum height of the scanned text. For example; when you set this to 0.5 it will be half size of scan frame


### `guideAreaWidthRatio`
Value: float from `0` to `1.0`

You can set the width of the frame scanner

### `zoomLevel`
Value: float from `1` to `3.0`

Specifies the zoom of your camera. The value 1 is no zoom, 3 is maximum zoom. For a medium zoom, for example, you could pass 2.


### `allowedChars`
Characters to be search

Type: `String`

Default Value: `"ABCDEFGHJKLMNPQRSTVXZ"`

### `minDigits`
Minimum character limit for searched code

### `maxDigits`
Maximum character limit for searched code

### `replaceMap`
An Object to replace characters on searched code. 

**Example**
```JSON
    {
        'characters to be replaced': 'replaced characters',
        'Ğ': 'G'
    }
```

### `validTextHighlightColor`
highlight color for validated code.


### `invalidTextHighlightColor`
highlight color for invalid code.

### `overlayColor`
Background color of camera overlay view.

### `resolution`
Resoulotion of the camera.

Values: `InstaScan.Constants.Resolution.UHD`, `InstaScan.Constants.Resolution.FHD`, `InstaScan.Constants.Resolution.HD`, `InstaScan.Constants.Resolution.VGA` 


### `guideTextColor`

Value: `#000`

Font color for the guide text.




## Examples

Usage
```javascript
import React, { useEffect, useRef } from 'react';
import { View, StyleSheet, TouchableOpacity, Text } from 'react-native';
import { InstaScan } from 'instascan-react-native';

export default function App() {
    const instaScan = useRef();

    useEffect(() => {
        //You can get torch status 
        instaScan.current.getTorchStatus()
        .then(status => console.log("@torch status", status));
    }, []);

    const handlePinCodeRead = event => {
        //you can reach code with event.pinCode
    }

    const onInstaScanError = error => {
        //handle error
    }

    const toggleTorch = () => {
        //This will toggle the torch
        instaScan.current.toggleTorch();
    }

    return (
        <View style={{flex: 1}}>
            <InstaScan 
                ref={instaScan}
                apiKey="XXXXX"
                style={{flex: 1}}
                onPinCodeRead={handlePinCodeRead}
                onError={onInstaScanError}
            />
            <TouchableOpacity 
              style={styles.torchButton}  
              onPress={toggleTorch}
            >
              <Text>Toggle Torch</Text>
            </TouchableOpacity>
        </View>
    )
}

const styles = StyleSheet.create({
  torchButton: {
    position: 'absolute',
    bottom: '5%',
    left: '5%',
    width: '90%',
    height: 50
  }
})
```

  
## Contact Us & Purchase

Please contact us through info@kaizentech.net, If you have any feedback or want to buy SDK


  
