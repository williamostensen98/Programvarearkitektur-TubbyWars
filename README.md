# Programvarearkitektur-TubbyWars
Project in TDT4240 Software Architecture

## Tubby Wars Game Application

TubbyWars is a fun and easy to play game that will get you stuck to your phone for hours! 
Use touch to drag and shoot your teletubby opponent with fireball bullets and receive score for each hit. 
Buy new and better weapons in the shop after each round with earned points. The better the weapon, the more damage and scorepoints. Play with another friend as many times you'd like, and try to earn as many points as you can. Maybe you'll even set a new highscore!
  
## 1 Demo/User Manual

The game is turned based and requires two player on the same device, so gather a friend and you're good to go!

* Press PLAY and the players will now be able to choose usernames and which teletubbby you want to play with.
* The shop will them appear and this is where you can buy weapons. First time playing both players will start with a standard revolver. 
* Start Playing! 
* Upon your turn, press your tubby and drag to aim and choose power of the shot. Let go to shoot. 
* You earn points for every time you hit the opponent and points can buy you new and improved weapons. 
* A round ends when a player looses all of its health. 
* After each round the players will take turn in buying new weapons in the shop if enough points have been earned. 
* Each new round is presented with a new and more difficult map and will increase the diffuculty of hitting the opponent.
* After a total of 5 rounds the game is over and the scores of the players will be presented as well as a highscore board. Did you end up on the board?


## 2 Technology and Frameworks
Built with:  
* [Android Studios](https://developer.android.com/studio/?gclid=CjwKCAjwv4_1BRAhEiwAtMDLsozFvTbQdD1gDDKtKmX58Udj2DvLeoF2hyhyzdcBArlbnsQCdAUq6RoCfMYQAvD_BwE&gclsrc=aw.ds) 
* [LibGDX](https://libgdx.badlogicgames.com/)
* [Ashley ECS](https://github.com/libgdx/ashley/wiki)
* [Box2D](https://box2d.org/)

## 3 Installation

### 3.1 Getting Started
To run the application you will need [Android Studio](https://developer.android.com/studio/?gclid=CjwKCAjwv4_1BRAhEiwAtMDLsozFvTbQdD1gDDKtKmX58Udj2DvLeoF2hyhyzdcBArlbnsQCdAUq6RoCfMYQAvD_BwE&gclsrc=aw.ds)(or a similar IDE).
To run the application you will need an Android device or emulator with API level 21 or higher. If you're not in possesion of a Android device, Android studio comes with the possibility to run on an emulator(see [3.2](#compile_and_run))

### 3.2 Cloning the repository
When Android Studio is dowloaded you are now ready to download the project itself. 
Open the terminal and navigate to the folder of your choosing: `cd <your-directory>`
You can now clone the project:

```java
git clone https://github.com/williamostensen98/Programvarearkitektur-TubbyWars.git
```

Open Android studios and choose `Open an existing Android Studio project`.
Navigate and choose the project just cloned. 
Wait for the Gradle Build to finish, and you are now ready to run the application. 

<a name="compile_and_run"></a>
### 3.3 Compiling and running 

To run the application you will need to connect your android device or install an android emulator. See steps below for both ways. 

#### 3.3.1 Running on Android device
* Connect your device to your computer via USB and make sure the device running is 64bit and runs API level 21 or higher(most newer model does).
* Enable needed permissions on your device such that the computer have access to it. 
* On the computer you can now select your device in `Connected Devices`.
* Press OK and run the app with the play button in the to right corner (or bottom left)

#### 3.3.2 Running with Android Emulator
* Open the AVD manager in the top drop down menu.
* Choose `+Create Virtual Device`
* Choose a device (for example the Galaxy Nexus) and press `Next`.
* Next when selecting a system image, go to `x86 images` in the menu
* Choose the Image you would like to download, but make sure the ABI i set to `x86_64`.
* When dowloaded, press `Next` and the `Finish`.
* Exit the AVD Manager and make sure the dowloaded device is set in the top menu of the IDE. 
* Press the play button beside it, wait for the build to complete and enjoy playing!
  
## Developed By
- Hanne Olssen
- Håkon Lia
- Jenny Almestad
- Sunniva Block
- William Østensen
- Åsne Stige
