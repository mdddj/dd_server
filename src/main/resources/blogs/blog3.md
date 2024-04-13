咳咳,先定义一个类集成`RouteObserver<PageRoute<dynamic>>`
```dart
import 'package:flutter/material.dart';
import '../h_utils/util/page_change_util.dart';
import '../event/route_event.dart';

class MyRouteObserver extends RouteObserver<PageRoute<dynamic>> {
  void _sendScreenView(PageRoute<dynamic> route, ActionType type) {
    var screenName = route.settings.name;
    var isCurrent = route.isCurrent;
    PageChangeUtil()
        .pushRouteDetail(RouteEvent(screenName ?? '', type, route, isCurrent));
  }

  @override
  void didPush(Route<dynamic> route, Route<dynamic> previousRoute) {
    super.didPush(route, previousRoute);
    if (route is PageRoute) {
      _sendScreenView(route, ActionType.DID_PUSH);
    }
  }

  @override
  void didReplace({Route<dynamic> newRoute, Route<dynamic> oldRoute}) {
    super.didReplace(newRoute: newRoute, oldRoute: oldRoute);
    if (newRoute is PageRoute) {
      _sendScreenView(newRoute, ActionType.DID_REPLACE);
    }
  }

  @override
  void didPop(Route<dynamic> route, Route<dynamic> previousRoute) {
    super.didPop(route, previousRoute);
    if (previousRoute is PageRoute && route is PageRoute) {
      _sendScreenView(previousRoute, ActionType.DID_POP);
    }
  }

  @override
  void didRemove(Route route, Route previousRoute) {
    super.didRemove(route, previousRoute);
    if(previousRoute is PageRoute && route is PageRoute){
      _sendScreenView(previousRoute, ActionType.DID_REMOVE);
    }
  }
}

```

把这个类加入到`MaterialApp`属性`navigatorObservers` 中
```dart
  navigatorObservers: [
    MyRouteObserver(),
    ...
  ]
```
model
 ```dart
import 'package:flutter/cupertino.dart';

class RouteEvent {
  PageRoute<dynamic> route;
  String pageNmae;
  ActionType type;
  bool isCurrent;
  RouteEvent(this.pageNmae, this.type, this.route,this.isCurrent);
}

enum ActionType { DID_PUSH, DID_REPLACE, DID_POP, DID_REMOVE }

```

然后复制我的工具类
```dart
import 'dart:async';

class PageChangeUtil {
  PageChangeUtil._internal() {
    init();
  }

  factory PageChangeUtil() => _instance;

  static final PageChangeUtil _instance = PageChangeUtil._internal();


  StreamController _controller;
  Stream _stream;

  static PageChangeUtil get instance => _instance;
  Stream get changeStream => _stream;
  StreamController get ctrl => _controller;

  void disposeStream() => _controller.close();

  void init() {
    _controller ??= StreamController.broadcast();
    _stream ??= _controller.stream;
    if(_controller.isClosed){
      _controller = null;
      _stream = null;
      init();
    }
  }

  void pushRouteDetail(dynamic data){
    init();
    _controller.sink.add(data);
  }
}

```
至此准备工作完成,使用也很简单
```dart
  @override
  void initState() {
    _listenPageChange();
    super.initState();
  }

 // 监听页面改变事件
  void _listenPageChange() {
    PageChangeUtil.instance.changeStream.listen((event) {

      switch(event.type){
        case ActionType.DID_REMOVE:
          break;
        case ActionType.DID_POP:
          print('页面返回');
          break;
        case ActionType.DID_PUSH:
          print('跳转到下一页');
          break;
        case ActionType.DID_REPLACE:
          break;
        default:
          break;
      }
    });
  }

  @override
  void dispose() {
    PageChangeUtil.instance.disposeStream();
    super.dispose();
  }
```