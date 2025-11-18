
# Jetpack Compose Performance & Recomposition Codelab

## Objectives

* Learn how **recomposition works** in Jetpack Compose.
* Understand **stable vs unstable state** using `@Stable` and `@Immutable`.
* Explore **Lazy layouts** and how keys reduce unnecessary recompositions.
* Use **Layout Inspector** and logs to analyze UI performance.


## Home Screen

The app starts with a **Home Screen**, allowing navigation to three main tasks:

* **Task 1:** Recomposition + Layout Inspector
* **Task 2:** @Stable and @Immutable
* **Task 3:** Lazy Layout + Key

Use the buttons to navigate between tasks.


# Step 1: Recomposition Playground

**Objective:** Understand recomposition and visualize it using **Layout Inspector**.

### Instructions

1. Open the **First Task Screen** from Home:  
   `First Task: Recomposition + LayoutInspector`

2. Observe the screen:

   * Two counter buttons (`Add Counter` / `Update to 1`)
   * Text to update the counter
* Infinite counter button
* Text to update the infinite counter

3. Open **Layout Inspector**:  
   `Tools > Layout Inspector` → enable **Toggle Layout Inspector**

4. Click **Add Counter** and **Update to 1**:

   * Watch which composables **turn blue** in Layout Inspector — these are recomposing.
   * Observe **logs** in Logcat:

   ```  
Recomposition : skippable counter 1  
Recomposition : skippable counter 2 ```
5. Start the **Infinite Counter** and see **continuous recompositions**.

### Key Takeaways

* Recomposition occurs **when state changes**.
* Only **composables that read the state** are recomposed.
* `SideEffect` + Logcat helps track recompositions.
* Layout Inspector visualizes recomposition in real time.


# Step 2: Explore @Stable and @Immutable

**Objective:** Learn how **stable vs unstable objects** affect recomposition.

### Instructions

1. Open **Second Task Screen** from Home:  
   `Second Task: @Stable and @Immutable`

2. Observe the screen:

   * `NewsData` object displayed via `ItemNewsContent`
* Button `Trigger Recomposition`

3. Examine the code:

```kotlin  
var newsData by remember {  
 mutableStateOf( NewsData( xid = "dasd", title = "Title ", datePublish = "Date ", slug = "Slug", tags = mutableListOf("Tag 1", "Tag 2") ) )}  
```  

* `MutableList` in `NewsData` is **not stable**.
* Clicking the button triggers recomposition **even if data hasn’t changed**.

4. **Experiment:**

* Click `Trigger Recomposition` multiple times.
   * Observe **Layout Inspector**: `ItemNewsContent` may recomposed unnecessarily.

5. **Optimize Stability:**

```kotlin  
@Immutable  
data class NewsData(  
 val xid: String,  
 val title: String = "", val datePublish: String = "", val slug: String = "", val thumbnail: Int = R.drawable._50_400x250, val tags: List<String> = emptyList() // immutable list)  
```  

* Using **immutable collections** and `@Immutable` allows Compose to **skip unnecessary recompositions**.

### Key Takeaways

* Mutable objects can trigger **extra recompositions**.
* Use **immutable data classes** and `@Immutable` for performance.
* Only **updating the object reference** triggers recomposition when the object is stable.


# Step 3: Lazy Layouts + Keys

**Objective:** Learn how **LazyColumn** and **keys** optimize list recomposition.

### Instructions

1. Open **Third Task Screen** from Home:  
   `Third Task: Lazy Layout + Key`

2. Observe:

   * List type selector (`Column`, `Lazy`, `Lazy With Key`)
* Search field
   * List of promo items

3. Select each **list type** and experiment:

| Type          | Behavior                                                            |  
| ------------- | ------------------------------------------------------------------- |  
| Column        | All items recompose on any change (inefficient for large lists) |  
| Lazy          | Only visible items are recomposed (some state may reset) |  
| Lazy With Key | Only affected items recompose, preserving state and scroll position |  

4. Type in the **Search Promo** field and observe **recomposition in Layout Inspector**.

### Key Code Snippets

**Column List:**

```kotlin  
Column {  
 filtered.forEach { data -> ItemHorizontalContent(title = data.title, promoData = data) { } }}  
```  

**LazyColumn with Keys:**

```kotlin  
LazyColumn {  
 items(items = filtered, key = { it.xid }) { data -> ItemHorizontalContent(title = data.title, promoData = data) { } }}  
```  

### Key Takeaways

* Regular `Column` is simple but inefficient for large lists.
* `LazyColumn` optimizes rendering by **only composing visible items**.
* Using **keys** ensures **recompositions are minimized** and item state is preserved.


# Summary

By completing all three steps, trainees learn:

1. **Recomposition basics** — only affected composables are recomposed.
2. **State stability** — immutable objects and `@Immutable` reduce unnecessary recompositions.
3. **Lazy layouts with keys** — efficiently render large lists while preserving state and performance.
4. **Debugging tools** — Layout Inspector and logs help visualize recomposition.

This codelab builds a foundation for **writing high-performance Compose UIs** and understanding **how Compose optimizes UI updates**.


## Optional Exercises

* Wrap `Counter` or `ItemNewsContent` in smaller composables to reduce recomposition scope.
* Experiment with adding new items in `LazyColumn` and see the effect of keys.
* Try combining multiple `@Immutable` data objects and observe performance improvements.