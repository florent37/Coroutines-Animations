# Coroutines Animations

[![CircleCI](https://circleci.com/gh/florent37/Coroutine-Animations/tree/master.svg?style=svg)](https://circleci.com/gh/florent37/Coroutine-Animations/tree/master)
[![Language](https://img.shields.io/badge/language-kotlin-brightgreen.svg)](https://github.com/florent37/Coroutine-Animations)

Use the power of kotlin coroutines to execute your android animations

[ ![Download](https://api.bintray.com/packages/florent37/maven/Coroutines-Animations/images/download.svg) ](https://bintray.com/florent37/maven/Coroutines-Animations/)
```groovy
implementation 'com.github.florent37:coroutine-animations:(last version)'
```

# Usage

TOTO : ADD VIDEO

```kotlin
launch(UI) {
    //fire an alpha animation without suspending coroutine
    animation(avatar) { alpha = 0.5f } 
 
    //execute a translation animation
    //use .join() to suspend the coroutine until the animation end
    animation(avatar, startDelay = 1000L) { y = 0f }.join()
 
    //run these animations in parallel
    mutableListOf<Job>(
           animation(follow) {
               top = avatar.y + avatar.height + 16f
           },
           animation(kotlin) {
               left = avatar.x - kotlin.width - 16f
               centerY = avatar.centerY()
           }
    )
    .forEach { it.join() } //wait until all animations have finished
}
```

# Simple animation

```kotlin
animation(view, startDelay= , duration=, interpolator=) { 
    property1 = value1 
    property2 = value1 
}
```

# Custom animation

Animation of `Float` values
```kotlin
floatAnimation(avatar, from=1f, to=0.5f){ view, value ->
    view.alpha = value
}
```

Animation of `Int` values
```kotlin
intAnimation(avatar, from=0, to=300){ view, value ->
    view.x = value
}
```

## Chain Animations

`.join()` suspend the coroutine until the animation has finished

```kotlin
animation(view1) { property1 = value1 }.join()
animation(view2) { property2 = value2 }.join()
```

## Animations in parallel

Just use animations as usual coroutine jobs, 
if you want to wait for multiple to finish, 
add them into a list and call `join` on each

```kotlin
val animations = mutableListOf<Job>(
    animation(view1) { property1 = value1 }
    animation(view2) { property2 = value2 }
)
animations.forEach { it.join() } // wait for all animations to complete
```

# How to Contribute

We welcome your contributions to this project. 

The best way to submit a patch is to send us a [pull request](https://help.github.com/articles/about-pull-requests/). 

To report a specific problem or feature request, open a new issue on Github. 

Fiches Plateau Moto : [https://www.fiches-plateau-moto.fr/](https://www.fiches-plateau-moto.fr/)

# License

    Copyright 2018 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
