# Coroutine Animation

TODO MAKE README

```kotlin
async {
    animation(avatar, startDelay = 1000L) { y = 0f }.join() //wait until animation end
    
    //run these animations in parallel
    mutableListOf<Job>(
           animation(follow) {
               top = avatar.y + avatar.height + 16f
           },
           animation(kotlin) {
               left = avatar.x - kotlin.width - 16f
               centerY = avatar.centerY()
           },
           animation(coroutine) {
               left = avatar.x + avatar.width + 16f
               centerY = avatar.centerY()
           }
    ).forEach { it.join() } // wait for all animations to complete
}
```

# Simple animation

```kotlin
animation(view, startDelay= , duration=) { 
    property1 = value1 
    property2 = value1 
}
```

# Custom animation

of float
```kotlin
floatAnimation(avatar, from=1f, to=0.5f){ view, value ->
    view.alpha = value
}
```

of Int
```kotlin
intAnimation(avatar, from=0, to=300){ view, value ->
    view.x = value
}
```

## Chain Animations

.join() will suspend the coroutine until the animation has finished

```kotlin
animation(view1) { property1 = value1 }.join()
animation(view2) { property2 = value2 }.join()
```

## Animations in parallel

```kotlin
val animations = mutableListOf<Job>(
    animation(view1) { property1 = value1 }
    animation(view2) { property2 = value2 }
)
animations.forEach { it.join() } // wait for all animations to complete
```