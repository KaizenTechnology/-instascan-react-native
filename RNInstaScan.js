// main index.js
import React, { Component } from "react";
import { NativeModules, Platform } from 'react-native';
import {
  UIManager,
  findNodeHandle
} from "react-native";
import RNInstaScan from "./ViewManager";



const CameraModule = NativeModules.KZNInstaScanView
export default class InstaScan extends Component {
  constructor(props){
    super(props);
    this.instaScanRef;
  }


  static Constants = {
    Algorithm: {
      ACCURATE: 0,
      FAST: 1
    },
    FocusRestriction: {
      NONE: 0,
      NEAR: 1,
      FAR: 2,
    },
    Resolution: {
      UHD: 0,
      FHD: 1,
      HD: 2,
      VGA: 3,
    }
  }




  
  componentDidMount(){
    setTimeout(() => {
      this.startScan();
    }, 0);  
  }

  componentWillUnmount(){
    this.stopScan();
  }


  render() {
    return (
      <RNInstaScan
        ref={ref => this.instaScanRef = ref}
        style = {{flex:1}}
        {...this.props}
        onPincodeRead={({nativeEvent}) => {
          this.updateGuideText(nativeEvent.pincode)
    
          if(this.props.onPincodeRead){
            if(typeof nativeEvent?.configuration === "string")
              nativeEvent.configuration = JSON.parse(nativeEvent.configuration);

              this.props.onPincodeRead(nativeEvent);

          }
        }}
        onInstaScanError={event => {
          if(this.props.onError)
            this.props.onError(event.nativeEvent);
        }}
      />
    );
  }




  startScan = (...args) => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.instaScanRef),
      "startScan",
      [...args]
    );
  };

  stopScan = (...args) => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.instaScanRef),
      "stopScan",
      [...args]
    );
  };

  restartScan = (...args) => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.instaScanRef),
      "restartScan",
      [...args]
    );
  };

  updateGuideText = (...args) => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.instaScanRef),
      "updateGuideText",
      [...args]
    );
  };

  toggleTorch = (...args) => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.instaScanRef),
      "toggleTorch",
      [...args]
    );
  };

  setTorch = (...args) => {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this.instaScanRef),
      "setTorch",
      [...args]
    );
  };

  getTorchStatus = () => {
    if(Platform.OS === "android")
      return CameraModule.getTorchStatus()
    else
      return CameraModule.getTorchStatus(findNodeHandle(this.instaScanRef))
  };


}



