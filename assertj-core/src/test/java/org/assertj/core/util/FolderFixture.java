/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2025 the original author or authors.
 */
package org.assertj.core.util;

import static java.io.File.separator;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.isNullOrEmpty;
import static org.assertj.core.util.Strings.concat;
import static org.assertj.core.util.Strings.quote;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Creates and deletes directories in the file system.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class FolderFixture {

  private static Logger logger = Logger.getLogger(FolderFixture.class.getName());

  private final List<FolderFixture> folders = new ArrayList<>();
  private final List<FileFixture> files = new ArrayList<>();

  private final String name;
  private final FolderFixture parent;

  private File dir;

  public FolderFixture(String name) {
    this(name, null);
  }

  public FolderFixture(String name, FolderFixture parent) {
    this.name = name;
    this.parent = parent;
    create();
  }

  File dir() {
    return dir;
  }

  private void create() {
    String path = relativePath();
    dir = new File(path);
    if (!dir.exists()) {
      assertThat(dir.mkdir()).isTrue();
      logger.info("Created directory %s".formatted(quote(path)));
      return;
    }
    if (!dir.isDirectory()) throw new AssertionError("%s should be a directory".formatted(quote(path)));
    logger.info("The directory %s already exists".formatted(quote(path)));
  }

  public FolderFixture addFolder(String folderName) {
    FolderFixture child = new FolderFixture(folderName, this);
    folders.add(child);
    return child;
  }

  public FolderFixture addFiles(String... names) throws IOException {
    for (String file : names)
      files.add(new FileFixture(file, this));
    return this;
  }

  public void delete() {
    for (FolderFixture folder : folders)
      folder.delete();
    for (FileFixture file : files)
      file.delete();
    String path = relativePath();
    boolean dirDeleted = dir.delete();
    if (!dirDeleted) throw new AssertionError("Unable to delete directory %s".formatted(quote(path)));
    logger.info("The directory %s was deleted".formatted(quote(path)));
  }

  String relativePath() {
    return parent != null ? concat(parent.relativePath(), separator, name) : name;
  }

  public FolderFixture folder(String path) {
    String[] names = path.split(separatorAsRegEx());
    if (isNullOrEmpty(names)) return null;
    int i = 0;
    if (!name.equals(names[i++])) return null;
    FolderFixture current = this;
    for (; i < names.length; i++) {
      current = current.childFolder(names[i]);
      if (current == null) break;
    }
    return current;
  }

  private FolderFixture childFolder(String folderName) {
    for (FolderFixture folder : folders)
      if (folder.name.equals(folderName)) return folder;
    return null;
  }

  private String separatorAsRegEx() {
    String regex = separator;
    if ("\\".equals(regex)) regex = "\\\\";
    return regex;
  }
}
